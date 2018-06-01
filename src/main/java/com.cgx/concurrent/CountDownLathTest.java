package com.cgx.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLathTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final CountDownLatch ct = new CountDownLatch(2);
		
		Thread t1 = new Thread(new  Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t1线程进入,等待其它线程执行完毕再执行");
					ct.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t1线程执行赛程");
			}
		});
		
		
		Thread t2 = new Thread(new  Runnable() {
			@Override
			public void run() {
				System.out.println("t2线程进入");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t2线程执行完毕,通知t1执行"+ct.getCount());
				ct.countDown();
			}
		});
		
		Thread t3 = new Thread(new  Runnable() {
			public void run() {
				System.out.println("t3线程进入");
				try {
					Thread.sleep(3000);
					throw new InterruptedException();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t3线程执行完毕");
				ct.countDown();
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}

}
