package com.cgx.zookeeker;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
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

    /****************
     * 同步创建节点
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void create(){
        System.out.println("开始创建节点");
        /**********
         * 创建/cgx节点，赋值chen
         * 节点类型(CreateMode)有：PERSISTENT: 持久化，PERSISTENT_SEQUENTIAL：待久化顺序，EPHEMERAL：临时，EPHEMERAL_SEQUENTIAL:临时顺序
         */
        String path = null;
        try {
            path = zk.create("/cgx1", "chen".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
             zk.create("/test1", "test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zk.create("/test2", "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("创建节点："+path);

    }

    //异步创建临时节点，这里等待它执行完看processResult
    public static void asnycCreateNode() throws Exception {
        System.out.println("asnycCreateNode----------------------");
        zk.create("/test3", "znode3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL, new IStringCallBack(), "context");
        Thread.sleep(2000);
    }

    /**********
     * 异步创建回调方法
     */
    static class IStringCallBack implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int rc, String path, Object ctx,
                                  String name) {
            System.out.println(
                    "Create path result " + rc + " " + path + " " + ctx + " " +
                            name);
        }
    }

    public static void clear(){
        try {
            Stat exists = zk.exists("/cgx1", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    Event.KeeperState state = event.getState();
                    System.out.println("是否存在"+event.getPath());
                }
            });
           if (exists!=null){
            zk.delete("/cgx1",-1);
               System.out.println("删除成功");
           }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //获取子节点列表，在子节点列表变更时触发
    public static void getChildren() throws Exception {
        System.out.println("getChildren----------------------");
        List<String> childList = zk.getChildren("/", new Watcher() {
            @Override
            //这个znode的子节点变化的时候会收到通知
            public void process(WatchedEvent watchedEvent) {
                System.out.println("子节点有变化getChildren " + watchedEvent);
            }
        });
        System.out.println("childList " + childList);
    }

    //获取数据，注册监听器，在znode内容被改变时触发
    public static void getData() throws Exception {
        System.out.println("getData----------------------");
        String ans1 = new String(zk.getData("/test1", false, null));
        String ans2 = new String(zk.getData("/test2", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("getData " + watchedEvent);
            }
        }, null));
        System.out.println("znode /test1 content is " + ans1);
        System.out.println("znode /test2 content is " + ans2);
    }

    //更新内容，会触发对应znode的watch事件
    public static void setData() throws Exception {
        System.out.println("setData----------------------");
        String data = "zNode22";
        zk.setData("/test2", data.getBytes(), -1);
        String ans2 = new String(zk.getData("/test2", false, null));
        System.out.println("setData to " + ans2);
    }

    //节点是否存在，watch监听节点的创建，删除以及更新
    public static void exists() throws Exception {
        System.out.println("exists----------------------");
        Stat stat = zk.exists("/test2", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("exists " + watchedEvent);
            }
        });
        System.out.println("stat is " + stat);
    }

    public static void delete() throws Exception {
        System.out.println("delete----------------------");
        zk.delete("/test2", -1);
        zk.delete("/test1", -1);
    }


    public static void main(String[] args) {
        try {
            startZK();
            create();
            asnycCreateNode();
           // clear();
            getData();
            getChildren();
           // setData();
            //setData();
           // delete();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
