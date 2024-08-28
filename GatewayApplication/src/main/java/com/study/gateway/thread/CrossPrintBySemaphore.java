package com.study.gateway.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过信号量（Semaphore）实现交叉打印
 * @author Jerry
 */
public class CrossPrintBySemaphore {
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static AtomicInteger count = new AtomicInteger(100);
    /*public static void main(String[] args) {

        Thread aThread = new Thread(()->{
            while(count.get() > 0){
                try {
                    semaphoreA.acquire();
                    System.out.println("a" + count);
                    count.decrementAndGet();
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread bThread = new Thread(()->{
            while(count.get() > 0){
                try {
                    semaphoreB.acquire();
                    System.out.println("b" + count);
                    count.decrementAndGet();
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("Let's go !! Justin !!!");
        aThread.start();
        bThread.start();

        try {
            bThread.join();
            aThread.join();
        } catch (InterruptedException e) {
            System.out.println("wwwwwooooooo!!!!");
        }

        System.out.println("OMG");
    }*/

}
