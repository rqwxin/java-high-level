package com.cgx.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

public class UseLinkingBlock {

	public static void main(String[] args) {
		LinkedBlockingQueue<String> lq = new LinkedBlockingQueue<String>();
	    
			lq.offer("a");
			lq.offer("b");
			lq.offer("c");
			lq.offer("d");
			lq.offer("e");
			lq.offer("f");
			System.out.println(lq.size());
		
	}

}
