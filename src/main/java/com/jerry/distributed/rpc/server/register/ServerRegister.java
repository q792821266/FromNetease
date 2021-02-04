package com.jerry.distributed.rpc.server.register;

/**
 * description: ServerRegister <br>
 * date: 2021/1/6 17:45 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/617:45
 * @version: 1.0 <br>
 */
public interface ServerRegister {

    /**
     * 注册服务
     * @param service 服务实例
     * @param protocol 服务使用协议
     * @param port 使用端口
     * @throws Exception
     */
    public void register(ServiceObject service,String protocol,int port) throws Exception;

    /**
     * 获取已注册的服务
     * @param serviceName 服务名
     * @return
     * @throws Exception
     */
    public ServiceObject getService(String serviceName) throws Exception;


}
