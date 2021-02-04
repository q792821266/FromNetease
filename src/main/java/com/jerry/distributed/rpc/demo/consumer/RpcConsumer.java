package com.jerry.distributed.rpc.demo.consumer;

import com.jerry.distributed.rpc.client.net.ClientStubProxyFactory;
import com.jerry.distributed.rpc.client.net.NettyNetClient;
import com.jerry.distributed.rpc.common.protocol.JavaSerializeMessageProtocol;
import com.jerry.distributed.rpc.common.protocol.MessageProtocol;
import com.jerry.distributed.rpc.demo.provider.DemoService;
import com.jerry.distributed.rpc.discovery.ZookeeperServiceInfoDiscover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * description: RpcConsumer <br>
 * date: 2021/1/4 22:01 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/422:01
 * @version: 1.0 <br>
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(RpcConsumer.class);
        ClientStubProxyFactory cspf = new ClientStubProxyFactory();
        //设置服务发现者
        cspf.setSid(new ZookeeperServiceInfoDiscover());
        Map<String, MessageProtocol> supportMessageProtocol = new HashMap<String, MessageProtocol>();
        supportMessageProtocol.put("javas", new JavaSerializeMessageProtocol());
        cspf.setSupportMessageProtocol(supportMessageProtocol);

        cspf.setNetClient(new NettyNetClient());

        DemoService demoService = cspf.getProxy(DemoService.class);
        String Hello = demoService.sayHello("world ~");
        logger.info(" 调用结果： {} ", Hello);

    }
}
