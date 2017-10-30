package com.yzyy.pro;

public class PData {

    private final int data;

    public PData(int data) {
        this.data = data;
    }

    public PData(String d) {
        data = Integer.parseInt(d);
    }

    public int getData() {
        return data;
    }

    @Override
    public String toString() {
        return "data:" + data;
    }
}
