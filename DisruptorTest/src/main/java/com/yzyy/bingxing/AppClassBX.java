package com.yzyy.bingxing;

public class AppClassBX {

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        for (int i = 1 ; i < 5; i++) {
            for (int j = 1 ; j  < 5; j ++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.orgString = "((" + i + "+" + j + ")*" + i + ")/2";
                Plus.queue.add(msg);
            }
        }
    }
}
