package com.yzyy.zh;

public class SingletonOne {

    private SingletonOne(){}

    private static SingletonOne instance = new SingletonOne();

    public static SingletonOne getInstance() {
        return instance;
    }
}
