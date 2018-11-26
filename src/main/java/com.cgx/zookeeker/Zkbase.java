package com.cgx.zookeeker;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2018-11-26 09:45
 **/
public class Zkbase {
    /*****
     * zookeeper 实例
     */
    private static ZooKeeper zk;
    /*********
     *顶层节点
     */
    private final static String ZKROOT="zookeeper";

    public static void startZK() throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //zk连接地址
        String zkIp = "127.0.0.1:2181";
        zk = new ZooKeeper(zkIp, 2000, new Watcher() {
            //创建zk 时的监听器
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("事件："+watchedEvent);
                //取得事件状态
                Event.KeeperState state = watchedEvent.getState();
                if (Event.KeeperState.SyncConnected.equals(state)){
                    //连接成功
                    System.out.println("zookeeper连接成功");
                }
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    public static void create(){
      //  zk.create
    }

    public static void main(String[] args) {
        try {
            startZK();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
