package com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.client;


/**
 * Created by Tom.
 */
public class Test {
    public static void main(String[] args) {
        KBMeipo gpMeipo = new KBMeipo();
        IStudent wangwu = gpMeipo.getInstance(new WangWu());
        wangwu.study("高等数学");
    }
}
