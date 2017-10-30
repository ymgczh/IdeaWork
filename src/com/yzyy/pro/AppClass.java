package com.yzyy.pro;

import java.util.concurrent.*;

public class AppClass {

    public static void main(String[] args) {
        BlockingDeque<PData> queue = new LinkedBlockingDeque<>(10);

        ExecutorService exe = Executors.newFixedThreadPool(20);

        Producer producer = null;
        Consumer consumer = null;
        for (int j = 0; j < 20 ; j++) {
            if (j % 2 == 0){
                producer = new Producer(queue);
                exe.execute(producer);
            }
            else {
                consumer = new Consumer(queue);
                exe.execute(consumer);
            }

            System.out.println("J::::" + j);
        }

        exe.shutdown();
    }
}
