package com.yzyy.xccsore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * java 线程池 submit和 execute有什么区别
 * 因为之前一直是用的execute方法，最近有个情况需要用到submit方法，所以研究了下。
 三个区别：
 1、接收的参数不一样
 2、submit有返回值，而execute没有
 Method submit extends base method Executor.execute by creating and returning a Future that can be used to cancel execution and/or wait for completion.
 用到返回值的例子，比如说我有很多个做validation的task，我希望所有的task执行完，然后每个task告诉我它的执行结果，是成功还是失败，如果是失败，原因是什么。然后我就可以把所有失败的原因综合起来发给调用者。
 个人觉得cancel execution这个用处不大，很少有需要去取消执行的。
 而最大的用处应该是第二点。
 3、submit方便Exception处理
 There is a difference when looking at exception handling. If your tasks throws an exception and if it was submitted with execute this exception will go to the uncaught exception handler (when you don't have provided one explicitly, the default one will just print the stack trace to System.err). If you submitted the task with submit any thrown exception, checked or not, is then part of the task's return status. For a task that was submitted with submit and that terminates with an exception, the Future.get will rethrow this exception, wrapped in an ExecutionException.
 意思就是如果你在你的task里会抛出checked或者unchecked exception，而你又希望外面的调用者能够感知这些exception并做出及时的处理，那么就需要用到submit，通过捕获Future.get抛出的异常。
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
                //意思是如果一旦捕获到异常，程序停止执行
                service.shutdown();
                e.printStackTrace();
                return;
            }
        }
    }
}

class TaskSub implements Callable<String>{

    private int i;

    private Random random = new Random();

    public TaskSub(int i) {
        this.i = i;
    }
    public String call() throws Exception {
        System.out.println( i + "  :call 开始干活  ：" + Thread.currentThread().getName());

        if (random.nextBoolean()) {
            throw new TaskException("error Task is : " + Thread.currentThread().getName());
        }
        Thread.sleep(1000);
        return Thread.currentThread().getName() + ":call 返回数据 i is : " + i;
    }
}

class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}