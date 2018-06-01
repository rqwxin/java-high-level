package com.cgx.master;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {
	
	//任务容器
	private ConcurrentLinkedQueue<Task> workQueue;
	//结果集
	private ConcurrentHashMap<String, Object> resultMap;
			
	public void run() {
		while(true){
			Task task = workQueue.poll();
			if(task == null) break;
			//真正处理业务
			try {
				System.out.println("worker在运行，任务："+task.getId());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			resultMap.put(task.getId()+"", task.getResult());
		}
	}

	public ConcurrentLinkedQueue<Task> getWorkQueue() {
		return workQueue;
	}

	public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public ConcurrentHashMap<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
