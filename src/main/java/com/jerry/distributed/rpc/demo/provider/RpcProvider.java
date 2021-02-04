package com.jerry.distributed.rpc.demo.provider;

import com.jerry.distributed.rpc.server.register.ServiceRegister;
import com.jerry.distributed.rpc.utils.PropertiesUtils;

/**
 * description: RpcProvider <br>
 * date: 2021/1/6 17:40 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/617:40
 * @version: 1.0 <br>
 */
public class RpcProvider {
    public static void main(String[] args) {
        int port = Integer.parseInt(PropertiesUtils.getProperties("rpc.port"));
        String protocol = PropertiesUtils.getProperties("rpc.protocol");

        ServiceRegister serviceRegister = new ZookeeperExportServiceRegister();
    }
}
