package com.cgx.nio;

import java.nio.IntBuffer;

public class TestIntBuffer {
	
	
	public static void main(String[] args) {
	/*	IntBuffer ib = IntBuffer.allocate(10);
		ib.put(13);
		ib.put(31);
		ib.put(153);
		//ib.flip();
		
		System.out.println(ib);
		System.out.println("位置："+ib.position());
		System.out.println("限制："+ib.limit());
		System.out.println("其中第一个元素："+ib.get(0));
		
		for(int i =0;i<ib.limit();i++){
			System.out.println("第"+(i+1)+"个元素为："+ib.get(i));
		}*/
		
		//wrap使用
		int[] array = new int[]{12,23,45};
		IntBuffer ib2= IntBuffer.wrap(array);
		
		System.out.println(ib2);
		System.out.println("位置："+ib2.position());
		System.out.println("限制："+ib2.limit());
		
		//其他方法使用
		
		IntBuffer ib = IntBuffer.allocate(10);
		ib.put(13);
		ib.put(31);
		ib.put(153);
		
		ib.duplicate();
		
	}
}
