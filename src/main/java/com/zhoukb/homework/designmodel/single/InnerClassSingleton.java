package com.zhoukb.homework.designmodel.single;

public class InnerClassSingleton {

    private InnerClassSingleton() {
    }

    public static InnerClassSingleton getInstance() {
        return InnerSingle.LAZY;
    }

    private static class InnerSingle{
        private static final InnerClassSingleton LAZY = new InnerClassSingleton();
    }
}
