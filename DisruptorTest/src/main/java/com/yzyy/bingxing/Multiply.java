package com.yzyy.bingxing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 乘法
 */
public class Multiply implements Runnable{

    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();
    public void run() {
        while (true) {
            try {
                Msg msg = queue.take();
                msg.i = msg.j * msg.i;
                Div.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
