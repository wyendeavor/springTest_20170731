package com.wy.middleware.zookeeper.distributed_id;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * Description:使用Twitter SnowflakeId算法生成分布式id
 *
 * @author wangyuan
 * Date: Created at 2019-02-13 17:30
 */
public class DistributedIdGeneratorUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedIdGeneratorUtil.class);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 多个zk服务以逗号分隔，如"127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002"
     */
    private static final String ZK_SERVER_URL = "127.0.0.1:2181";

    /**
     * zookeeper连接超时时间
     */
    private static final int ZK_CONN_TIMEOUT = 2000;

    /**
     * 生成分布式自增唯一整数值的工具类
     */
    private static SnowflakeIdWorker snowflakeIdWorker;

    /**
     * zookeeper客户端实例对象
     */
    private static ZooKeeper zookeeper;

    /**
     * zookeeper创建临时顺序节点的path
     */
    private static final String EPHEMERAL_SEQUENTIAL_PATH = "/zk_incr_seq_node/increasing_id_generate_";

    /**
     * 初始化生成id的实例化对象
     */
    public static void initSnowflakeIdInstance() {
        try {
            // 创建ZK client对象
            zookeeper = new ZooKeeper(ZK_SERVER_URL, ZK_CONN_TIMEOUT, new ZkWatcherEvent());

            countDownLatch.await();

            String ephemeralSequentialPath = zookeeper.create(EPHEMERAL_SEQUENTIAL_PATH, "使用临时顺序增长节点获取唯一增长id".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            String identifyIdStr = ephemeralSequentialPath.substring(EPHEMERAL_SEQUENTIAL_PATH.length());

            // 由于Twitter SnowflakeId算法有10位用于表示应用服务器，因此对zookeeper的自增整数值取模
            long identifyId = Long.parseLong(identifyIdStr) % 1024;

            long fiveDigitNum = 0x000000000000001FL;
            long workerId = identifyId & fiveDigitNum;
            long datacenterId = (identifyId >>> 5);

            LOGGER.info("workerId:" + workerId + ", datacenterId:" + datacenterId);

            snowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("initial zookeeper client exception, please check the reason...");
        }
    }


    /**
     * 根据twitter snowflake算法生成Id，获取十进制的长整型字符串
     */
    public static long getNextSnowflakeId() {
        return snowflakeIdWorker.nextId();
    }


    /**
     * 根据twitter snowflake算法生成Id
     * @return snowflakeId的16进制的字符串
     */
    public static String getUniqueSixteenId(){
        return Long.toHexString(snowflakeIdWorker.nextId());
    }


    /**
     * zookeeper监视器类
     */
    public static class ZkWatcherEvent implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                LOGGER.info("监听到zookeeper事件:" + event.toString());
                countDownLatch.countDown();
            }
        }

    }


    public static void main(String[] args) {
        initSnowflakeIdInstance();

        long startTime = System.currentTimeMillis();

        for(int i=0; i< 1000000; i++) {
            long distributedId = getNextSnowflakeId();
            if(i==0 || i==999999) {
                LOGGER.info("index:{}, id:{}" + distributedId);
            }
        }

        long endTime = System.currentTimeMillis();

        LOGGER.info("生产100w的id耗时:{}", endTime - startTime);

    }



}
