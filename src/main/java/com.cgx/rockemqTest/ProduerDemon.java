package com.cgx.rockemqTest;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ProduerDemon {

	public static void main(String[] args) {
		//定义mq生产者，参数是组
		final DefaultMQProducer producer = new DefaultMQProducer("group");
		//设置服务地址
		producer.setNamesrvAddr("127.0.0.1:9876");
		 try {
			 producer.start();
			
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					try {
						for (int i = 0; i < 10; i++) {
							Message message = new Message("order", "tag", ("订单"+System.currentTimeMillis()).getBytes());
							SendResult result = producer.send(message);
							System.out.println(result);
							Thread.sleep(100);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			timer.schedule(task, new Date(), 5000);;
		 } catch (Exception e) {
			e.printStackTrace();
		} 
	}


}
