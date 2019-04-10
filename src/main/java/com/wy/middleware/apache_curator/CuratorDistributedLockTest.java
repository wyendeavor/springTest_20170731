package com.wy.middleware.apache_curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 使用Curator工具类封装zookeeper实现分布式锁
 *
 * @author wangyuan
 * Date: Created at 2019-04-10 16:23
 */
public class CuratorDistributedLockTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorDistributedLockTest.class);

    /**
     * 重试策略
     */
    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);

    /**
     * CuratorFramework Client实例
     */
    private static final CuratorFramework curatorFrameworkClient = CuratorFrameworkFactory.newClient("127.0.0.1:2181",
            retryPolicy);

    /**
     * 分布式锁的路径
     */
    private static final String LOCK_PATH = "/zk_curator_test/distributed_lock";

    /**
     * 线程池
     */
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);


    @Test
    public void distributedLockTest() throws Exception {
        curatorFrameworkClient.start();

        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorDistributedLockTest.acquireLockAndHandleBiz(curatorFrameworkClient, LOCK_PATH);
                        countDownLatch.countDown();
                    } catch (Exception ex) {
                        LOGGER.error("#CuratorDistributedLockTest.acquireLockAndHandleBiz exception, detail:", ex);
                    }
                }
            });
        }

        countDownLatch.await();
        LOGGER.info("Completed successful...");
    }


    /**
     * 分布式锁测试
     */
    public static void acquireLockAndHandleBiz(CuratorFramework client, String lockPath) throws Exception {
        InterProcessMutex interProcessMutex = new InterProcessMutex(client, lockPath);

        try {
            interProcessMutex.acquire();

            LOGGER.info("#CuratorDistributedLockTest success acquire lock, Thread Id: {}", Thread.currentThread().getId());

            LOGGER.info("#CuratorDistributedLockTest execute business logic....");
            Thread.sleep(100L);

            LOGGER.info("#CuratorDistributedLockTest success release lock, Thread Id: {}", Thread.currentThread().getId());


        } catch (Exception ex) {
            LOGGER.error("#CuratorDistributedLockTest exception, detail:", ex);
        } finally {
            interProcessMutex.release();
        }


    }


}
