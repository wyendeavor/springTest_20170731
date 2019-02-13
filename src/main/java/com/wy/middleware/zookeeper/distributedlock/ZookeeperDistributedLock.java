package com.wy.middleware.zookeeper.distributedlock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description: 使用ZK实现分布式锁
 *
 * @author wangyuan
 * Date: Created at 2018-11-19 23:12
 * @see <a href="https://blog.csdn.net/desilting/article/details/41280869">参考文档</a>
 */
public class ZookeeperDistributedLock implements Watcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperDistributedLock.class);

    public static final String HELLO_WORLD = "Hello World";

    private int threadId;

    private String threadPrefix;

    private ZooKeeper zk = null;

    /**
     * 确保zk连接成功
     */
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    /**
     * 线程个数
     */
    private static final int THREAD_NUM = 10;

    /**
     * 控制等待所有线程执行完毕
     */
    private CountDownLatch threadSemaphore = new CountDownLatch(THREAD_NUM);

    private String selfPath;

    private String waitPath;

    public static final int SESSION_TIMEOUT = 100000;

    public static final String GROUP_PATH = "/distributedLocks";

    private static final String SUB_PATH = "/distributedLocks/sub";

    public static final String CONNECTION_STRING = "localhost:2181";

    public ZookeeperDistributedLock(int threadId) {
        this.threadId = threadId;
        this.threadPrefix = "[第" + threadId + "个线程]";
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent == null) {
            return;
        }

        LOGGER.info("thread:{} receive an event, eventType:{}, nodePath:{}.", threadPrefix, watchedEvent.getType(),
                watchedEvent.getPath());

        Event.KeeperState keeperState = watchedEvent.getState();
        Event.EventType eventType = watchedEvent.getType();

        if (Event.KeeperState.SyncConnected == keeperState) {
            if (eventType == Event.EventType.None) {
                LOGGER.info("thread:{} connect to server successfully.", threadPrefix);
                connectedSemaphore.countDown();
            } else if (eventType == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(waitPath)) {
//            } else if (eventType == Event.EventType.NodeDeleted) {
                LOGGER.info("thread:{} receive previous node delete watch event, check whether it is turn to me...", threadPrefix);
                try {
//                    boolean getLockFlag = getLock();
//                    if (getLockFlag) {
//                        handleBizLogic();
//                    }
                } catch (Exception ex) {
                    LOGGER.info("thread:{} process watch event exception, detail:", ex);
                }
            }
        } else if (keeperState == Event.KeeperState.Disconnected) {
            LOGGER.info("thread:{} zk connection is disconnect from zk server...", threadPrefix);
        } else if (keeperState == Event.KeeperState.AuthFailed) {
            LOGGER.info("thread:{} zk connect authentication failed...", threadPrefix);
        } else if (keeperState == Event.KeeperState.Expired) {
            LOGGER.info("thread:{} zk connect expired...", threadPrefix);
        } else {
            LOGGER.info("therad:{} zk watch other exception...", threadPrefix);
        }


    }

    /**
     * 创建zk连接
     */
    public void createZkConnection(String connStr, int sessionTimeout) throws IOException, InterruptedException {
        this.zk = new ZooKeeper(connStr, sessionTimeout, this);
        connectedSemaphore.await(10000, TimeUnit.MILLISECONDS);
    }


    /**
     * 创建zk path
     */
    public boolean createZkPath(String path, String data, boolean needWatch) throws KeeperException, InterruptedException {
        if (zk.exists(path, needWatch) == null) {
            try {
                String newNodePath = zk.create(path, data.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
                LOGGER.info("zk create path successful, new path:{}", newNodePath);
            } catch (Exception ex) {
                LOGGER.error("thread:" + threadPrefix + ", zk create path exception, path:" + path + ", detail:", ex);
            }
        }
        selfPath = zk.create(SUB_PATH, HELLO_WORLD.getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        return true;
    }


    /**
     * 获取锁
     */
    public boolean getLock() throws KeeperException, InterruptedException {
        LOGGER.info("thread:{}, zk begin acquire lock, zk node path:{}", threadPrefix, selfPath);

        return checkMinPath();
    }

    /**
     * 处理业务逻辑
     */
    public void handleBizLogic() throws KeeperException, InterruptedException {
        if (zk.exists(this.selfPath, false) == null) {
            LOGGER.info("zk current node not exist...");
            return;
        }

        LOGGER.info("thread:{}, nodePath:{},  get zk lock success, begin work...", threadPrefix, selfPath);
        Thread.sleep(2000);
        LOGGER.info("thread:{}, end work, delete node...", threadPrefix);
        zk.delete(selfPath, -1);
        // 释放zk连接
        releaseZkConnection();
        // 运行中的线程数减少1
        threadSemaphore.countDown();
    }

    /**
     * 释放zk连接
     */
    public void releaseZkConnection() {
        try {
            this.zk.close();
            LOGGER.info("zk release connection successfully...");
        } catch (Exception ex) {
            LOGGER.error("zk close connection exception, detail:", ex);
        }
    }


    /**
     * 检查当前的节点是不是最小节点
     */
    public boolean checkMinPath() throws KeeperException, InterruptedException {
        List<String> subNodeList = zk.getChildren(GROUP_PATH, false);
        Collections.sort(subNodeList);
        int index = subNodeList.indexOf(selfPath.substring(GROUP_PATH.length() + 1));

        switch (index) {
            case -1:
                LOGGER.info("zk current node is not exist...");
                return false;
            case 0:
                LOGGER.info("zk current node is the minimum node, i will get lock...");
                return true;
            default:
                LOGGER.info("zk current node is not the minimum node, continue wait...");
                try {
                    this.waitPath = GROUP_PATH + "/" + subNodeList.get(index - 1);
                    LOGGER.info("zk get previous node, waitPath:{}", waitPath);
                    byte[] previousNodeData = zk.getData(waitPath, true, new Stat());
                    LOGGER.info("zk previous node data:{}", new String(previousNodeData, StandardCharsets.UTF_8));
                } catch (Exception ex) {
                    /*if (zk.exists(waitPath, false) == null) {
                        LOGGER.info("zk previous node was not exists, haha, checkMinNode again..");
                        return checkMinPath();
                    } else {
                        LOGGER.error("zk get previous node data exception, detail:", ex);
                    }*/
                }
                return false;
        }

    }


    public int getThreadId() {
        return threadId;
    }

    public String getThreadPrefix() {
        return threadPrefix;
    }
}


