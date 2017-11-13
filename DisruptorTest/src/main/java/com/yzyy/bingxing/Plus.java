package com.yzyy.bingxing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 加法
 */
public class Plus implements Runnable{

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();

    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                msg.j = msg.i + msg.j;
                Multiply.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
