package com.jerry.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description: ThreadPoolTest <br>
 * date: 2021/7/22 11:54 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/7/2211:54
 * @version: 1.0 <br>
 */
@Slf4j
public class ThreadPoolTest {

    /**
     * 提交15个任务，每个任务执行3秒  ，观察线程池的状况
     * @param threadPoolExecutor
     * @throws Exception
     */
    public void test(ThreadPoolExecutor threadPoolExecutor) throws Exception{
        for (int i = 0;i<15;i++){
            final int n = i;
            threadPoolExecutor.submit(new Runnable(){

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
}
