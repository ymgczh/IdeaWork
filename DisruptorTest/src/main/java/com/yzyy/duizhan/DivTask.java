package com.yzyy.duizhan;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: YMGC
 * @Description:
 * @Date: Created in 10:48 2017/11/27
 * @Since: 2017/11/27
 */
public class DivTask implements Runnable {
    int a, b;
    public DivTask( int a, int b) {
        this. a= a;
        this. b= b;
    }
    @Override
    public void run() {
        double re= a/ b;
        System.out.println(re);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor esp = new TraceThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            esp.execute(new DivTask(100, i));
        }
    }
}
