package com.jerry.distributed.rpc.server.register;

import java.util.HashMap;
import java.util.Map;

/**
 * description: DefaultServiceRegister <br>
 * date: 2021/1/6 22:57 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/622:57
 * @version: 1.0 <br>
 */
public class DefaultServiceRegister implements ServiceRegister{

    private Map<String,ServiceObject> serviceMap = new HashMap<String,ServiceObject>();

    public void register(ServiceObject service, String protocol, int port) throws Exception {
        if(service == null){
            throw new IllegalArgumentException("参数不能为空");
        }
        this.serviceMap.put(service.getName(), service);
    }

    public ServiceObject getServiceObject(String name) throws Exception {
        return null;
    }
}
