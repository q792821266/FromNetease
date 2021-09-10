package com.jerry.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * description: ThreadPoolTest <br>
 * date: 2021/7/22 11:54 <br>
 *
 * Q: 线程池队列为runnable的数据封装，那怎么提交callable或者future?
 * @author: Jerry <br>
 * @Date: 2021/7/2211:54
 * @version: 1.0 <br>
 */
@Slf4j
public class ThreadPoolCase {

    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();

    /**
     * 提交15个任务，每个任务执行3秒  ，观察线程池的状况
     * @param threadPoolExecutor
     * @throws Exception
     */
    public void testCommon(ThreadPoolExecutor threadPoolExecutor) throws Exception{
        int taskCount = 15;
        for (int i = 0; i< taskCount; i++){
            final int n = i;
            threadPoolExecutor.submit(new Runnable(){
                @Override
                public void run() {
                    try {
                        log.info("线程{} 开始执行...",n);
                        Thread.sleep(3000);
                        log.info("线程{} 完成执行。",n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            log.info("任务提交成功");
        }
        Thread.sleep(500L);
    }


    /**
     * 核心线程5，最大线程10，超出核心线程数量的线程存活时间为5秒，无界队列，默认拒绝策略（抛异常）
     * @throws Exception
     */
    private void testCase1() throws Exception{

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,10,5, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),threadFactory);
        testCommon(threadPool);
        //预期结果 ：超出核心线程数量的任务讲会在队列中等待
    }


    /**
     * 核心5 最大10，队列大小3，最大任务数13，指定了拒绝时的操作
     * @throws Exception
     */
    private void testCase2() throws Exception{
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3), threadFactory,
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        log.error("有任务被拒绝执行");
                    }
                });
        testCommon(threadPool);
        /*预计结果：
        * 1. 5个任务直接开始执行
        * 2. 3个任务进入等待队列
        * 3. 加开5个线程执行任务
        * 4. 多余的任务（超13个）被拒绝执行
        * 5. 任务完成5秒后加开的线程被销毁
        * */

    }

    private void testCase3() throws Exception{
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
        testCommon(threadPoolExecutor);
        /**
         * 预计结果：
         * 超过5个线程任务 进入等待队列
         */
    }

    /**
     * 核心线程0，最大数量为Integer.MAX_VALUE SynchronousQueue队列，线程存活时间60s
     *
     * @throws Exception
     */
    private void testCase4() throws Exception{

        // SynchronousQueue，实际上它不是一个真正的队列，因为它不会为队列中元素维护存储空间。与其他队列不同的是，它维护一组线程，这些线程在等待着把元素加入或移出队列。
        // 在使用SynchronousQueue作为工作队列的前提下，客户端代码向线程池提交任务时，
        // 而线程池中又没有空闲的线程能够从SynchronousQueue队列实例中取一个任务，
        // 那么相应的offer方法调用就会失败（即任务没有被存入工作队列）。
        // 此时，ThreadPoolExecutor会新建一个新的工作者线程用于对这个入队列失败的任务进行处理（假设此时线程池的大小还未达到其最大线程池大小maximumPoolSize）。

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),threadFactory);
        testCommon(threadPoolExecutor);

        Thread.sleep(6000L);
        log.info("60s后，线程池中线程数据量为{}", threadPoolExecutor.getPoolSize());

         // 预计结果：

    }

    /**
     * 5.定时执行线程池：3s后执行，一次性任务，到点就执行
     * 核心线程数5，最大数量为Integer.MAX_VALUE,使用DelayedWorkQueue(延时队列)，超出核心线程数的线程存活时间0s
     * @throws Exception
     */
    private void testCase5() throws Exception{
        ScheduledThreadPoolExecutor threadPoolExecutor5 = new ScheduledThreadPoolExecutor(5, threadFactory);
        threadPoolExecutor5.schedule(new Runnable() {

            public void run() {
                log.info("任务执行中，执行时间 {}", System.currentTimeMillis());
            }
        }, 3000, TimeUnit.SECONDS);
        log.info("定时任务提交完成，时间为{},当前线程池线程数量为：{}", System.currentTimeMillis(), threadPoolExecutor5.getPoolSize());
        //预计结果，任务在3s后被执行一次

    }

    private void testCase6() throws Exception{
        ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(5, threadFactory);

        /*
        周期性地执行任务，线程池提供了两种调度方法，这是第一种
        效果： 任务执行需要3s才能执行完成，提交之后，2s后开始第一次执行，之后每隔1s固定执行1次，如果上次任务还没有执行完成就会等待任务执行完成，
        完成后立刻执行下一个任务。没有等待时间。
        */
        threadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                log.info("任务-1 被执行，现在时间为：{}",System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.SECONDS);


        /*
        提交任务后2s开始执行，之后每隔1s执行,固定执行1次.如果上次任务没有执行完成，会等待完成后再等1s再执行下一个任务。
         */
        threadPool.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("任务-2 被执行，现在时间为：{}", System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.SECONDS);
    }


    private void testCase7(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3), threadFactory, new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                log.info("任务被拒绝执行。");
            }
        });

        for (int i = 0; i < 15; i++) {

        }
    }
}
