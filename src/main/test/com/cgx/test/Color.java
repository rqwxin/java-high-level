package com.cgx.test;

/**********
 * @program: java-high-level
 * @description: 测试单个类的执行顺序问题
 * @author: cgx
 * @create: 2019-08-08 10:54
 **/
public class Color {
    public static final String color = "red";//新增一段常量，编译阶段户直接放入常量池
    //构造函数
    public Color() {
        System.out.println("构造函数执行");
    }
    //静态代码块
    static{
        System.out.println("静态代码块执行");
    }
    //非静态代码块
    {
        System.out.println("非静态代码块执行");
    }
    //一般方法
    void run(){
        System.out.println("一般方法执行");
    }
    public static void main(String[] args) {
        System.out.println("main方法执行");//这个一定要写在最上面，程序一进入马上就执行，不然会导致结果不准确
        Color color = new Color();
        color.run();
    }

}
