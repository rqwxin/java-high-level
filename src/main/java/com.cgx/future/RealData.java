package com.cgx.future;

public class RealData implements Data {
	private String dealResult;
	
	
	public String getResult() {
		//真实处理数据方法
		//可能会操作数据库
		//模拟处理时间比较久
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dealResult = "操作后返回结果";
		return dealResult;
	}


	public String getDealResult() {
		return dealResult;
	}


	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}



}
