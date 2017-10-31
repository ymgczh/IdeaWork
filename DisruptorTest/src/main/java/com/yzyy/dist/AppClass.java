package com.yzyy.dist;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppClass {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        PDataFactory dataFactory = new PDataFactory();
        int bufferSize = 1024;
        Disruptor<PData> disruptor = new Disruptor<PData>(dataFactory,
                bufferSize,
                executor,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );
        disruptor.start();

        RingBuffer<PData> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            System.out.println("add data :" + l);
            bb.putLong(0, l);
            producer.pushData(bb);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
