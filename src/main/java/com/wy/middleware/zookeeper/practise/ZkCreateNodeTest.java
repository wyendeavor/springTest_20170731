package com.wy.middleware.zookeeper.practise;

import org.apache.commons.codec.Charsets;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * Description: zookeeper创建节点测试
 *
 * @author wangyuan
 * Date: Created at 2019-02-13 18:01
 */
public class ZkCreateNodeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZkCreateNodeTest.class);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 多个zk服务以逗号分隔，如"127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002"
     */
    private static final String ZK_SERVER_URL = "127.0.0.1:2181";

    /**
     * zookeeper连接超时时间
     */
    private static final int ZK_CONN_TIMEOUT = 5000;

    /**
     * zookeeper创建临时顺序节点的path
     */
    private static final String EPHEMERAL_SEQUENTIAL_PATH = "/zk_incr_seq_node/increasing_id_generate_";


    public static void main(String[] args) throws Exception {
        // 创建ZK client对象
        ZooKeeper zookeeper = new ZooKeeper(ZK_SERVER_URL, ZK_CONN_TIMEOUT, new ZkWatcherEvent());

//        countDownLatch.await();

        for (int i = 0; i < 3; i++) {
            String ephemeralSequentialPath = zookeeper.create(EPHEMERAL_SEQUENTIAL_PATH, "使用临时顺序增长节点获取唯一增长id".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            String identifyIdStr = ephemeralSequentialPath.substring(EPHEMERAL_SEQUENTIAL_PATH.length());
            long identifyId = Long.parseLong(identifyIdStr) % 1023;

            long fiveDigitNum = 0x000000000000001FL;
            long endFiveDigitNum = identifyId & fiveDigitNum;
            long frontFiveDigitNum = (identifyId >>> 5);

            LOGGER.info("created ephemeral sequential path:{}, generateId:{}", ephemeralSequentialPath, identifyId);
            LOGGER.info("frontFiveDigitNum:{}, endFiveDigitNum:{}", frontFiveDigitNum, endFiveDigitNum);
        }

    }


    public static class ZkWatcherEvent implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                countDownLatch.countDown();
            }
        }
    }

}
