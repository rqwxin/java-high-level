package com.cgx.future;

public class FutureClient {

	public Data request(String request){
		final FutureData f = new FutureData();
		//模拟一个线程来操作
		new Thread(new Runnable() {
			@Override
			public void run() {
				RealData r = new RealData();
				f.setRealData(r);
			}
		}).start();
		return f;
	}

}
