package com.cgx.future;

/***************
 * 模拟请求数据对象
 * @author xin
 *
 */
public class FutureData implements Data {
	//真实处理对象
	protected RealData realData =null;
	//是否已装载了真实处理对象
    protected boolean isReady = false;  
    
    //
    public synchronized String getResult() throws Exception {
    	//当真实处理对象还没有加载时，就一直等待真实处理对象
		while(!isReady){
			wait();//wait 释放锁
		}
		return realData.getResult();
	}
    
    public synchronized void setRealData(RealData realData){
    	if(isReady){
    		return;
    	}
    	this.realData = realData;
    	isReady = true;
    	notify();
    }
}
