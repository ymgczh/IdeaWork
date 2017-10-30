package com.yzyy.zh;

public class SingletonThree {

    private SingletonThree(){
        System.out.println("单例模式被创建");
    }

    private static class SingletonHolder {
        private static SingletonThree instance = new SingletonThree();
    }

    public static SingletonThree getInstance() {
        return SingletonHolder.instance;
    }

}
