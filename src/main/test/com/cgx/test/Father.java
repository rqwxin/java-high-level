package com.cgx.test;

/**********
 * @program: java-high-level
 * @description: 测试继承类的执行顺序
 * @author: cgx
 * @create: 2019-08-08 11:02
 **/
public class Father {
    public static  int fatherStaticNum = 100;
    public   int num = 200;
    //构造函数
    public Father() {
        System.out.println("父类构造函数执行");
    }
    //静态代码块
    static{
        System.out.println("父类静态代码块执行");
    }
    //非静态代码块
    {
        System.out.println("父类非静态代码块执行");
    }
    //一般方法
    void run(){
        System.out.println("父类一般方法");
    }
}

class Son extends  Father{
    //构造函数
    public Son() {
        System.out.println("子类构造方法");
    }

    //静态代码块
    static{
        System.out.println("子类静态代码块");
    }

    //非静态代码块
    {
        System.out.println("子类非静态代码块");
    }

    //子类重写一般方法
    @Override
    void run() {
        System.out.println("子类重写一般方法执行");
    }
    public static void main(String[] args) {
        System.out.println("main方法执行");
        Son son = new Son();
        son.run();
    }
}
