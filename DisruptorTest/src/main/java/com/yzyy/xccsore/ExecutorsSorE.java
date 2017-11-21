package com.yzyy.xccsore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * java 线程池 submit和 execute有什么区别
 */
public class ExecutorsSorE {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(20);
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < 20; i++) {
            final Future<String> futureString = service.submit(new TaskSub(i));
            futureList.add(futureString);
        }
        service.shutdown();
        for (Future future: futureList) {
            try {
                String st = (String)future.get();
                System.out.println(st);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class TaskSub implements Callable<String>{

    private int i;

    public TaskSub(int i) {
        this.i = i;
    }

    public String call() throws Exception {
        System.out.println( i + "  :call 开始干活  ：" + Thread.currentThread().getName());
        Thread.sleep(1000);
        return Thread.currentThread().getName() + ":call 返回数据 i is : " + i;
    }
}