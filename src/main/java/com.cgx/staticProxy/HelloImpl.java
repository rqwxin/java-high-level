package com.cgx.staticProxy;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2018-11-30 15:44
 **/
public class HelloImpl implements HelloApi {
    @Override
    public void say(String name) {
        System.out.println("hello "+name);
    }
}
