package com.cgx.future;

public class Circle extends GraphicBase{
	 public float PI = 3.14f;
	public float radius = 0f; 
	public Circle(String name, float radius){
		this.radius = radius;
		this.name = name;
	}
	public void caluCircum(){
		circum = 2*PI*radius;
		System.out.println("circum is" + circum);
	}
	public void caluAtea(){
		area = PI*radius*radius;
		System.out.println("area is" + area);
	}
	
}


 class GraphicBase{
	public String name;//图形名称变量名为name， 类型是String
	public float circum;//周长变量名为circum,类型是float
	public float area;//面积变量名为area, 类型是float

	public GraphicBase(){//构造方法，为属性进行初始化
		name = "";
		circum = 0.0f;
		area = 0.0f;
	}

	public void displayInfo(){//成员方法，作用是显示图形参数
		System.out.println(name);
		System.out.println(circum);
		System.out.println(area);
	}
	
}