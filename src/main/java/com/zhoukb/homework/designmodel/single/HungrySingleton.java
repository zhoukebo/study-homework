package com.zhoukb.homework.designmodel.single;

public class HungrySingleton {
    private static HungrySingleton instance;

    static {
        instance = new HungrySingleton();
    }

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
