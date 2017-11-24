package com.yzyy.executor;

import java.util.concurrent.*;

/**
 * @Author: YMGC
 * @Description: 自定义线程池的实现
 * @Date: Created in 15:34 2017/11/24
 * @Since: 2017/11/24
 */
public class ExecutorSelfDemo {

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
        /**
         * corePoolSize： 指定 了 线程 池 中的 线程 数量。
         * maximumPoolSize： 指定 了 线程 池 中的 最大 线程 数量。
         * keepAliveTime： 当 线程 池 线程 数量 超过 corePoolSize 时， 多余 的 空闲 线程 的 存活 时间。 即， 超过 corePoolSize 的 空闲 线程， 在 多长 时 间内， 会被 销毁。
         * unit： keepAliveTime 的 单位。
         * workQueue： 任务 队列， 被 提交 但 尚未 被 执行 的 任务。
         * threadFactory： 线程 工厂， 用于 创建 线程， 一般用 默认 的 即可。
         * handler： 拒绝 策略。 当 任务 太多 来不及 处理， 如何 拒绝 任务。
         */
        ExecutorService service = new ThreadPoolExecutor(5, 5,
                1000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                });

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            service.submit(task);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
