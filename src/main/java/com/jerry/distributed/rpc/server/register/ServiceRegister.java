package com.jerry.distributed.rpc.server.register;

/**
 * description: ServiceRegister <br>
 * date: 2021/1/6 22:32 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/622:32
 * @version: 1.0 <br>
 */
public interface ServiceRegister {

    /**
     * 服务注册
     * @param service 服务对象实体
     * @param protocol 归属协议
     * @param port 使用端口
     * @throws Exception 异常
     */
    void register(ServiceObject service, String protocol, int port) throws Exception;

    /**
     * 获取服务实例
     * @param name 对应服务名
     * @return 返回服务实例对象
     * @throws Exception 异常
     */
    ServiceObject getServiceObject(String name) throws Exception;
}
