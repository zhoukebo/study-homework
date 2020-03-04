package com.zhoukb.homework.designmodel.single;

public class SingleTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(InnerClassSingleton.getInstance()));
        Thread thread1 = new Thread(() -> System.out.println(InnerClassSingleton.getInstance()));

        thread.start();
        thread1.start();

    }
}
