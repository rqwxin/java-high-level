package com.cgx.concurrent;

import java.util.ArrayList;
import java.util.List;

public class Tickets {

	private  List<String> link = new ArrayList<String>();
	
	public Tickets(){
		for (int i = 0; i < 11; i++) {
			link.add("第"+i+"张");
		}
	}
	
	public synchronized  void add(String ticket) {
		link.add(ticket);
	}
	
	public synchronized void takeTicket() throws Exception{
		if(!link.isEmpty()){
			String ticket = link.remove(0);
			System.out.println("正在消费"+ticket);
			Thread.sleep(500);
		}else{
			System.out.println("票已买完了");
		}
	}
}
