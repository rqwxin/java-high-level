package com.cgx.future;

public class TestCircle {
	public static void main(String[] args) {
		//实例化一个半径为2 的圆
		Circle c = new Circle("一个圆",3f);
	     //圆周长
		c.caluCircum();
	    //圆面积
		c.caluAtea();
		
		c.displayInfo();
	}
}
