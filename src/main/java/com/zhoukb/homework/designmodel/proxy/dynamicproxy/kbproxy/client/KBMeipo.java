package com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.client;

import com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.proxy.KBClassLoader;
import com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.proxy.KBInvocationHandler;
import com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.proxy.KBProxy;

import java.lang.reflect.Method;

public class KBMeipo implements KBInvocationHandler {
    private IStudent target;
    public IStudent getInstance(IStudent target){
        this.target = target;
        Class<?> clazz =  target.getClass();
        return (IStudent)KBProxy.newProxyInstance(new KBClassLoader(),clazz.getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    private void after() {
        System.out.println("双方同意，开始交往");
    }

    private void before() {
        System.out.println("我是媒婆，已经收集到你的需求，开始物色");
    }
}
