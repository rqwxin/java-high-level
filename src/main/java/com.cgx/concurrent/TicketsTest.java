package com.cgx.concurrent;

public class TicketsTest {
    
	private final static int i = 10;
	public static void main(String[] args) {
		final Tickets t = new Tickets();
		for(int i = 1; i <=1000; i ++){
			new Thread("线程"+i){
				public void run(){
					try {
						t.takeTicket();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	

}
