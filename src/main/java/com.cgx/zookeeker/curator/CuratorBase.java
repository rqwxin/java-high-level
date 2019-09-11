package com.cgx.zookeeker.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.cgx.zookeeker.Zkbase.setData;

public class CuratorBase {
	
	/** zookeeper地址 */
	static final String CONNECT_ADDR = "127.0.0.1:2181";
	/** session超时时间 */
	static final int SESSION_OUTTIME = 5000;//ms

	static  CuratorFramework cf = null;

	private  static void   connZk(){
		//1 重试策略：初试时间为1s 重试10次
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
		//2 通过工厂创建连接
		cf = CuratorFrameworkFactory.builder()
				.connectString(CONNECT_ADDR)
				.sessionTimeoutMs(SESSION_OUTTIME)
				.retryPolicy(retryPolicy)
//					.namespace("super")
				.build();
		//3 开启连接
		cf.start();
	}

		//建立节点 指定节点类型（不加withMode默认为持久类型节点）、路径、数据内容
	public  static  void  createNode(){
		try {
			System.out.println("----建立节点-----");
			String path = cf.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super/c1", "c1内容".getBytes());
			 path += cf.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/super/c2", "c2内容".getBytes());
			System.out.println("建立节点："+path);
		} catch (Exception e) {
			System.out.println("建立节点异常");
			e.printStackTrace();
		}
	}
	//删除节点
	static void delNode(){
		try {
			System.out.println("----删除节点-----");
			cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");
			System.out.println("删除节点");
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
	}

	static void getChildren(){
		try {
			System.out.println("----读取子节点-----");
			List<String> paths = cf.getChildren().forPath("/super");
			for (String path:
				 paths) {
				System.out.println("/super下的子节点："+path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void inBackgroud(){
		// 绑定回调函数
		 ExecutorService pool = Executors.newCachedThreadPool();
		try {
			cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
			.inBackground(new BackgroundCallback() {
		   @Override
		   public void processResult(CuratorFramework cf, CuratorEvent ce) throws Exception {
			   System.out.println("code:" + ce.getResultCode());
			   System.out.println("type:" + ce.getType());
			   System.out.println("线程为:" + Thread.currentThread().getName());
		   }
		   }, pool)
			.forPath("/super/c3","c3内容".getBytes());
			//Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void checkExsits(){
		System.out.println("-------验证节点状态--------");
		try {
			Stat stat = cf.checkExists().forPath("/super/c1");
			System.out.println(stat.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void getData(){
		try {
			System.out.println("----读取节点-----");
			String result = new String(cf.getData().forPath("/super/c1"));
			System.out.println("读取数据："+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void setData(){
		try {
			System.out.println("----修改节点-----");
			cf.setData().forPath("/super/c1", "修改c1内容".getBytes());
			String ret2 = new String(cf.getData().forPath("/super/c1"));
			System.out.println(ret2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static  void getLeader(){
        LeaderLatch leaderLatch = new LeaderLatch(cf, "/super/leader", "Client #" +8);
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
        try {
            leaderLatch.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) throws Exception {
		//创建CuratorFramework
	   connZk();
		// 新加
		createNode();
		// 读取
		getData();
		// 修改节点
		setData();
		//子节点
		getChildren();
		// 绑定回调函数
		inBackgroud();
		checkExsits();
		//删除
		delNode();

        getLeader();

		
		// 读取子节点getChildren方法 和 判断节点是否存在checkExists方法
		  /**

		Stat stat = cf.checkExists().forPath("/super/c3");
		System.out.println(stat);
		
		Thread.sleep(2000);
		cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");
		*/

		//cf.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");
		if (cf!=null){
			cf.close();
		}
		System.out.println("退出");
	}
}
