package com.cgx.zookeeker.curator;

import com.google.common.collect.Lists;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-08-08 17:42
 **/
public class LeaderSelectListner {

    private static final String PATH = "/examples/leader2";
    static  String zkhosts = "localhost:2181";
    public static void main(String[] args) throws Exception {
        setUp();
//        example1();
    }

    private static void example1() throws Exception {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
        try {
            for (int i = 0; i < 10; ++i) {
            RetryPolicy retry = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                    .connectString(zkhosts)
                    .retryPolicy(retry)
                    .sessionTimeoutMs(6000)
                    .connectionTimeoutMs(3000).build();
                clients.add(curatorFramework);


            System.out.println( "======ZOOKEEPER ");
            LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, PATH, "Client #" +i);
                leaderLatch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                      System.out.println( "======ZOOKEEPER CURATOR  I am leader. I am dojob! id = "+leaderLatch.getId());

                    }
                    @Override
                    public void notLeader() {
                        System.out.println("===========I am not leader. I will do nothing!  id="+leaderLatch.getId());
                    }
                });
                examples.add(leaderLatch);
                curatorFramework.start();
                leaderLatch.start();
            }
        } finally {
            System.out.println("Shutting down...");

        }
    }

    public  static  void setUp() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkhosts)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();

        LeaderLatch leaderLatch = new LeaderLatch(client, PATH, "serverId"+Math.random());
        leaderLatch.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                System.out.println("Currently run as leader");
            }

            @Override
            public void notLeader() {
                System.out.println("Currently run as slave");
            }
        });
        leaderLatch.start();
        Thread.sleep(2000);
        System.out.println(leaderLatch.hasLeadership()+"----"+leaderLatch.getState());
    }

}
