package com.cgx.master;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	
	//承装任务的并发对列容器
	private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();
	//承装worker容器
	private Map<String,Thread> workMap = new HashMap<String, Thread>();
	
	//归纳收集结果
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	/******************
	 * 
	 * @param workerCount worker分配数
	 * @param worker 
	 */
	public Master(int workerCount,Worker worker){
		// 每一个worker对象都需要有Master的引用 workQueue用于任务的领取，resultMap用于任务的提交
		worker.setWorkQueue(taskQueue);
		worker.setResultMap(resultMap);
		for(int i=0;i<workerCount;i++){
			workMap.put("子节点"+i, new Thread(worker));
		}
	}
	
	/******************
	 * 启动master线程
	 */
	public void executer(){
	    for(Map.Entry<String, Thread> thread :workMap.entrySet()){
	    	thread.getValue().start();
	    }
	}
	
	public void add(Task task){
		taskQueue.add(task);
	}
	
	//获取结果集
    public int getResult(){
    	int result = 0;
    	for(Map.Entry<String, Object> map : resultMap.entrySet()){
    		Object o = map.getValue();
    		if(o instanceof Integer){
    			result+= ((Integer) o).intValue();
    		}
    	}
    	
    	return result;
    }
    
    public boolean isComplete(){
    	for(Map.Entry<String, Thread> thread :workMap.entrySet()){
	    	Thread t = thread.getValue();
	    	if(t.getState() != Thread.State.TERMINATED){
	    		return false;
	    	}
	    }
    	return true;
    }
}
