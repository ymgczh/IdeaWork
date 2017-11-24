package com.yzyy.executor;

import java.util.concurrent.*;

/**
 * @Author: YMGC
 * @Description: 自定义线程池，创建ThreadFactory
 * 自定义 线程 池 可以 帮助 我们 做 不少 事。 比如， 我们 可以 跟踪 线程 池 究竟 在 何时 创建 了 多少 线程，
 * 也可以 自定义 线程 的 名称、 组 以及 优先级 等 信息， 甚至 可以 任 性地 将 所有 的 线程 设置 为 守护 线程。
 * 总之， 使用 自定义 线程 池 可以 让我 们 更加 自由 地 设置 池子 中 所有 线程 的 状态。 下面 的 案例 使用 自定义 的 ThreadFactory，
 * 一方面 记录 了 线程 的 创建， 另一方面 将 所有 的 线程 都 设置 为 守护 线程，
 * 这样， 当 主线 程 退出 后， 将会 强制 销毁 线程 池。
 * @Date: Created in 15:58 2017/11/24
 * @Since: 2017/11/24
 */
public class ThreadFactoryDemo {

    public static class MyTask implements Runnable {
        public void run() {
            System. out. println( System. currentTimeMillis() + ":Thread ID:"  + Thread. currentThread(). getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true); //设置为守护线程
                        System.out.println("create: " + t);
                        return t;
                    }
                });

        for (int i = 0; i < 5; i++) {
            es.submit(task);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
