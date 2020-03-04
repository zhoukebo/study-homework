package com.zhoukb.homework.designmodel.proxy.dynamicproxy.kbproxy.client;

public class WangWu implements IStudent {

    @Override
    public void study(String bookName) {
        System.out.println("正在学习:" + bookName + ";");
    }

    @Override
    public void findLove(String firstName, Integer height) {
        System.out.println("张三要求：姓氏：" + firstName + ",身高：" + height);
    }

    @Override
    public void buyInsure() {
        System.out.println("30万");
    }

}
