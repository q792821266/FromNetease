package com.jerry.distributed.rpc.demo.provider;

import java.awt.*;

/**
 * description: DemoService 提供直接服务<br>
 * date: 2021/1/6 10:41 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/610:41
 * @version: 1.0 <br>
 */
public interface DemoService {

    /**
     * HELLO WORLD
     * @return
     */
    String sayHello(String name);

    /**
     * 点的乘法
     * @param a
     * @param b
     * @param multiple
     * @return
     */
    Point mutiplyPoint(Point a,Point b,int multiple);

}