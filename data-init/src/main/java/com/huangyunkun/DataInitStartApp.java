package com.huangyunkun;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Hello world!
 */
public class DataInitStartApp {
    public static void main(String[] args) throws Exception {
        String zookeeperHost = System.getenv("DUBBO_ZOOKEEPER_ADDRESS");

        if (zookeeperHost == null || "".equalsIgnoreCase(zookeeperHost)) {
            zookeeperHost = "127.0.0.1";
        }

        System.out.println("zookeeper host: " + zookeeperHost);

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperHost + ":2181", retryPolicy);
        client.start();

        Stat stat = client.checkExists().forPath("/dubbo/config/dubbo/dubbo.properties");

        if (stat == null) {
            String data = "dubbo.metadata-report.address=zookeeper://" + zookeeperHost + ":2181\n"
                    + "dubbo.registry.address=zookeeper://" + zookeeperHost + ":2181";
            client.create().creatingParentsIfNeeded().forPath("/dubbo/config/dubbo/dubbo.properties", data.getBytes());
        }
    }
}
