package com.yzyy.pro;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable {

    private BlockingDeque<PData> queue;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingDeque<PData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id is :" + Thread.currentThread().getId());

        Random random = new Random();
        try {
            while (true) {
                PData data = queue.take();
                if (null != data) {
                    int re = data.getData() * data.getData();
                    System.out.println("take data  :" + MessageFormat.format("{0} * {1} = {2}",//
                            data.getData(), data.getData(), re));
                    Thread.sleep(random.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
