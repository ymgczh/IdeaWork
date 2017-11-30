package com.yzyy.rwlock;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: YMGC
 * @Description: 读写锁DEMO
 * @Date: Created in 9:23 2017/11/22
 * @Since: 2017/11/22
 */
public class ReadWriteLockDemo {

    private static Lock reentrantLock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    /**
     * 读锁
     * @param lock
     * @return
     * @throws InterruptedException
     */
    public Object handleReadLock(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read：" + value);
            return value;
        }  finally {
            lock.unlock();
        }
    }

    /**
     * 写锁
     * @param lock
     * @param index
     * @throws InterruptedException
     */
    public void handleWriteLock(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write: " + index );
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            public void run() {
                try {
                    demo.handleReadLock(readLock);
//                    demo.handleReadLock(reentrantLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            Random random = new Random(100);
            public void run() {
                try {
                    demo.handleWriteLock(writeLock, random.nextInt());
//                    demo.handleWriteLock(reentrantLock, random.nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
