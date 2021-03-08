package com.jerry.distributed.rpc.demo.provider;

import com.jerry.distributed.rpc.common.protocol.JavaSerializeMessageProtocol;
import com.jerry.distributed.rpc.demo.provider.impl.DemoServiceImpl;
import com.jerry.distributed.rpc.server.NettyRpcServer;
import com.jerry.distributed.rpc.server.RequestHandler;
import com.jerry.distributed.rpc.server.RpcServer;
import com.jerry.distributed.rpc.server.register.ServiceObject;
import com.jerry.distributed.rpc.server.register.ServiceRegister;
import com.jerry.distributed.rpc.server.register.ZookeeperExportServiceRegister;
import com.jerry.distributed.rpc.utils.PropertiesUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * description: RpcProvider <br>
 * date: 2021/1/6 17:40 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/617:40
 * @version: 1.0 <br>
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(PropertiesUtils.getProperties("rpc.port"));
        String protocol = PropertiesUtils.getProperties("rpc.protocol");

        ServiceRegister serviceRegister = new ZookeeperExportServiceRegister();
        DemoService demoService = new DemoServiceImpl();
        ServiceObject serviceObject = new ServiceObject(DemoService.class.getName(), DemoService.class, demoService);
        serviceRegister.register(serviceObject, protocol, port);

        RequestHandler requestHandler = new RequestHandler(new JavaSerializeMessageProtocol(), serviceRegister);

        RpcServer server = new NettyRpcServer(port,protocol,requestHandler);
        System.out.println("启动服务");
        server.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            server.stop();
        }while(("quit").equals(bufferedReader.readLine()));
        System.out.println("停止服务");
    }
}
