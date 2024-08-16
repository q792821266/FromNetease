package com.study.gateway.thread;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印数据的方式一 : 使用reentrantLock
 * @author Jerry
 */
public class CrossPrintByReentrantLock {
    private static AtomicInteger printTimes = new AtomicInteger(100);
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static final Condition condition = reentrantLock.newCondition();
    private static final Condition condition2 = reentrantLock.newCondition();
    private static volatile boolean isFirstThreadRun = true;

    @Async()
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (printTimes.get() > 0) {
                    reentrantLock.lock();
                    try {
                        while (!isFirstThreadRun) {
                            condition.await();
                        }
                        System.out.println("a" + printTimes.get());
                        isFirstThreadRun = false;
                        printTimes.decrementAndGet();
                        condition2.signal();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("a thread interrupt");
                    } finally {
                        reentrantLock.unlock();

                    }


                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (printTimes.get() > 0) {
                    reentrantLock.lock();
                    try {
                        while (isFirstThreadRun) {
                            condition2.await();
                        }
                        System.out.println("b" + printTimes.get());
                        isFirstThreadRun = true;
                        printTimes.decrementAndGet();
                        condition.signal();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("b thread interrupt");
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }
        });
        System.out.println("Thread is started");
        thread.start();
        thread2.start();
    }
}
