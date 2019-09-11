package com.cgx.test;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-08-07 09:44
 **/
public class SynchronizedExam {
    public static void main(String[] args) {
        new SynchronizedExam();
    }

    SynchronizedExam(){
        SynchronizedExam a1 = this;
        SynchronizedExam a2 = this;
        synchronized (a1){
            try {
                a2.wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }catch (Exception e){
                System.out.println("Exception");
            }finally {
                System.out.println("finally");
            }
            System.out.println("all Done");
        }
    }
}
