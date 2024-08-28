package com.study.hc.foundation.thread;

/**
 * 线程死锁
 * @author Jerry
 */
public class DeadLockThread {
    public void waitNotificationDeadLockTest() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this) {
                System.out.println("get the lock and ready to wait");
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("method ended");
            }
        });
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this) {
            this.notify();
            System.out.println("release the lock and notify the waiting thread");
        }
    }

    public void crxLockObject(){
        Object ob1 = new Object();
        Object ob2 = new Object();

        Thread t1 = new Thread(()->{
            synchronized(ob1){
                System.out.println("get the lock of ob1");
                try {
                    Thread.sleep(3000);
                    synchronized (ob2){
                        System.out.println("get the lock of ob2");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized(ob2){
                System.out.println("get the lock of ob2");
                try {
                    Thread.sleep(3000);
                    synchronized (ob1){
                        System.out.println("get the lock of ob1");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        DeadLockThread threadSample = new DeadLockThread();
        //threadSample.waitNotificationDeadLockTest();
        threadSample.crxLockObject();
    }
}