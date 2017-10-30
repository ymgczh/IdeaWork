package com.yzyy.pro;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者类
 */
public class Producer implements Runnable{

    private volatile boolean isRuning = true;

    private BlockingDeque<PData> queue;

    private static AtomicInteger count = new AtomicInteger();

    private static final int SLEEPTIME = 1000;

    public Producer(BlockingDeque<PData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PData pdata = null;
        Random random = new Random();

        System.out.println("start producer id:" + Thread.currentThread().getId());

        try {
            int i = 0;
            while (isRuning) {
                Thread.sleep(random.nextInt(SLEEPTIME));
                pdata = new PData(count.incrementAndGet());
                System.out.println(pdata + ": is put into queue. i::" + i);
                if (!queue.offer(pdata, 2, TimeUnit.SECONDS)) {
                    System.out.println("failed to put data :" + pdata);
                }
                i ++;
                if (i == 20) {
                    isRuning = false;
                }
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {

    }
}
