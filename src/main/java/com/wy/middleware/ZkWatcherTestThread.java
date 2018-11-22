package com.wy.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 测试zookeeper分布式锁的线程
 *
 * @author wangyuan
 * Date: Created at 2018-11-21 21:25
 */
public class ZkWatcherTestThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkWatcherTestThread.class);

    /**
     * zookeeper分布式锁的对象
     */
    private ZookeeperDistributedLock zookeeperDistributedLock;

    public ZkWatcherTestThread(ZookeeperDistributedLock zookeeperDistributedLock) {
        this.zookeeperDistributedLock = zookeeperDistributedLock;
    }

    @Override
    public void run() {
        try {
            zookeeperDistributedLock.createZkConnection(ZookeeperDistributedLock.CONNECTION_STRING,
                    ZookeeperDistributedLock.SESSION_TIMEOUT);
            zookeeperDistributedLock.createZkPath(ZookeeperDistributedLock.GROUP_PATH, ZookeeperDistributedLock.HELLO_WORLD,
                    true);
            boolean getLockFlag = zookeeperDistributedLock.getLock();
            while(!getLockFlag) {
                Thread.sleep(500);
                getLockFlag = zookeeperDistributedLock.getLock();
            }
            zookeeperDistributedLock.handleBizLogic();

        } catch (Exception ex) {
            LOGGER.info("thread:" + zookeeperDistributedLock.getThreadPrefix() + " run excetpion, detail:", ex);
        }
    }
}
