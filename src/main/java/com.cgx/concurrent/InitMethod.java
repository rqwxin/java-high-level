package com.cgx.concurrent;

public class InitMethod {
	public InitMethod(){
		System.out.println(1);
	}
	public InitMethod(String i){
		System.out.println(2);
	}
	
	public static void main(String[] args) {
		InitMethod2 m = new InitMethod2("fk");
	}
}

class InitMethod2 extends InitMethod{
	public InitMethod2(){
		System.out.println(3);
	}
	public InitMethod2(String i){
		System.out.println(4);
	}
	
}