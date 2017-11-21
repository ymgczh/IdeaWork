package com.yzyy.xlh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量机制
 */
public class XhlDemo implements Runnable{

    final Semaphore semaphore = new Semaphore(5);

    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getId() + " :semaphore out");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exeu = Executors.newFixedThreadPool(20);

        final XhlDemo xhl = new XhlDemo();
        for (int i = 0; i < 20; i++) {
            exeu.submit(xhl);
        }
    }
}
