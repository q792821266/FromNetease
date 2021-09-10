package com.jerry.distributed.rpc.demo.provider.impl;

import com.jerry.distributed.rpc.demo.provider.DemoService;

import java.awt.*;

/**
 * @ClassName DemoServiceImpl
 * @Description demoService的实现
 * @Author Jerry
 * @Date 2021/2/4 21:38
 * @Version 1.0
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public Point multiplyPoint(Point a, int multiple) {
        a.x *= multiple;
        a.y *= multiple;
        return a;
    }


}
