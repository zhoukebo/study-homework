package com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.proxy;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2019/3/10.
 */
public interface KBInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
