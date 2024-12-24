package com.study.hystrix.command;

import com.netflix.hystrix.*;
import org.springframework.web.client.RestTemplate;

public class CustomerCommand extends HystrixCommand<Object> {

    private RestTemplate restTemplate;

    public CustomerCommand(RestTemplate restTemplate) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("study-hystrix"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("ConsumerController"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("studyHystrix-ThreadPool"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000)
                        .withCircuitBreakerSleepWindowInMilliseconds(5000))//withCircuitBreakerSleepWindowInMilliseconds 断路器打开后多久后才允许再次尝试
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(1).withMaxQueueSize(2)));

        this.restTemplate = restTemplate;

    }

    @Override
    protected Object run() throws Exception {
        //核心实现
        System.out.println("当前线程"+Thread.currentThread().getName());
        return restTemplate.getForObject("http://helloserver/index", String.class, "");
    }

    @Override
    protected Object getFallback() {
        System.out.println("fallback");
        return "服务已降级。。。";
    }
}
