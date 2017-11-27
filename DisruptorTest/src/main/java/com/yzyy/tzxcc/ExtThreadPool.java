package com.yzyy.tzxcc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: YMGC
 * @Description: 拓展线程池:
 * Ncpu = CPU 的 数量
 * Ucpu = 目标 CPU 的 使用 率， 0 ≤ Ucpu ≤ 1
 * W/ C = 等待 时间 与 计算 时间 的 比率 为 保持 处理器 达到 期望 的 使用 率，
 * 最优 的 池 的 大小 等于：
 * Nthreads = Ncpu * Ucpu * ( 1 + W/ C )
 * @Date: Created in 9:48 2017/11/27
 * @Since: 2017/11/27
 */
public class ExtThreadPool {

    public static class MyTask implements Runnable {
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println("正在执行：" + Thread.currentThread().getId() + " /  Task Name : " + name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(5,
                5,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行：" + ((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成：" + ((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            MyTask myTask = new MyTask("Task-Name: " + i);
            //这里用submit报错
//            es.submit(myTask);
            es.execute(myTask);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("CPU数量：" + Runtime.getRuntime().availableProcessors());
        es.shutdown();
    }
}
