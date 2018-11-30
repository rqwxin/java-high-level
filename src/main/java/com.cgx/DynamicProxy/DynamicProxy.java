package com.cgx.DynamicProxy;

import com.cgx.staticProxy.HelloApi;
import com.cgx.staticProxy.HelloImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2018-11-30 15:51
 **/
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return  result;
    }

    private void after() {
        System.out.println("after dynamicProxy invoke");
    }

    private void before() {
        System.out.println("before dynamicProxy invoke");
    }

    public  <T> T getProxy(){
        Object instance = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);

        return (T) instance;

    }
    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        HelloApi proxy = dynamicProxy.getProxy();
        proxy.say("肾");
    }
}
