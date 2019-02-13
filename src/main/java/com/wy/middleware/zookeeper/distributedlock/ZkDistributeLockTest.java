package com.wy.middleware.zookeeper.distributedlock;

/**
 * Description:zookeeper分布式锁测试
 *
 * @author wangyuan
 * Date: Created at 2018-11-21 21:40
 */
public class ZkDistributeLockTest {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            ZookeeperDistributedLock zkDistributedLock = new ZookeeperDistributedLock(i);
            ZkWatcherTestThread zkWatcherTestThread = new ZkWatcherTestThread(zkDistributedLock);
            new Thread(zkWatcherTestThread).start();
        }

        Thread.sleep(30000);
    }


}
