package com.jerry.distributed.rpc.server.register;

import org.I0Itec.zkclient.ZkClient;

/**
 * description: ZookeeperExportServiceRegister <br>
 * date: 2021/1/6 22:51 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/622:51
 * @version: 1.0 <br>
 */
public class ZookeeperExportServiceRegister extends DefaultServiceRegister implements ServiceRegister {

    private ZkClient zkClient;

    private String centerRootPath = "/Rpc-frameWork";

    public ZookeeperExportServiceRegister() {
        String addr = 
    }

    @Override
    public void register(ServiceObject service, String protocol, int port) throws Exception {

    }

    @Override
    public ServiceObject getServiceObject(String name) throws Exception {
        return null;
    }
}
