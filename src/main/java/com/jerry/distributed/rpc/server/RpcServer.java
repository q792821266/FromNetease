package com.jerry.distributed.rpc.server;

import com.jerry.distributed.rpc.server.RequestHandler;
import lombok.Data;

/**
 * @ClassName RpcServert
 * @Description server启动抽象类
 * @Author Jerry
 * @Date 2021/2/23 15:06
 * @Version 1.0
 */
@Data
public abstract class RpcServer {
    protected int port;
    protected String protocol;
    protected RequestHandler requestHandler;


    public RpcServer(int port, String protocol, RequestHandler requestHandler) {
        super();
        this.port = port;
        this.protocol = protocol;
        this.requestHandler = requestHandler;
    }

    /**
     * @Description 启动
     * @Author Jerry
     * @Date 15:46 2021/2/23
     * @Param  * @param 
     * @return void
     **/
    public abstract void start();

    /**
     * @Description 停止服务
     * @Author Jerry
     * @Date 16:08 2021/2/23
     * @Param  * @param
     * @return void
     **/
    public abstract void stop();

}
