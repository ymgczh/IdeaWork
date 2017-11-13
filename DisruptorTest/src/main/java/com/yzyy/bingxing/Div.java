package com.yzyy.bingxing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 除法
 */
public class Div implements Runnable{

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();
    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                msg.i = msg.i / 2;
                System.out.println(msg.orgString + " = " + msg.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
