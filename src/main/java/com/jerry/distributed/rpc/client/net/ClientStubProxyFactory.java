package com.jerry.distributed.rpc.client.net;

import com.jerry.distributed.rpc.common.protocol.MessageProtocol;
import com.jerry.distributed.rpc.common.protocol.RpcRequest;
import com.jerry.distributed.rpc.common.protocol.RpcResponse;
import com.jerry.distributed.rpc.discovery.ServiceInfo;
import com.jerry.distributed.rpc.discovery.ServiceInfoDiscover;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * description: ClientStubFactory <br>
 * date: 2021/1/3 23:06 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/323:06
 * @version: 1.0 <br>
 */
@Data
public class ClientStubProxyFactory {

    private ServiceInfoDiscover sid;
    private Map<String, MessageProtocol> supportMessageProtocol;
    private NetClient netClient;
    private Map<Class<?>, Object> objectCache = new HashMap<Class<?>, Object>();

    @SneakyThrows
    public <T> T getProxy(Class<T> interf) {
        T obj = (T) this.objectCache.get(interf);
        if (obj == null) {
            obj = (T) Proxy.newProxyInstance(interf.getClassLoader(), new Class<?>[]
                    {interf}, new ClientStubProxyFactoryHandler(interf));
            this.objectCache.put(interf, obj);
        }
        return obj;
    }

    @Getter
    private class ClientStubProxyFactoryHandler implements InvocationHandler {
        private Class<?> interf;

        private Random random = new Random();

        public <T> ClientStubProxyFactoryHandler(Class<?> interf) {
            super();
            this.interf = interf;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if("toString".equals(method.getName())){
                return proxy.getClass().toString();
            }

            if("hashCode".equals(method.getName())){
                return 0;
            }

            //获取服务信息
            String serviceName = this.interf.getName();
            List<ServiceInfo> serviceInfos = sid.getServiceInfos(serviceName);

            if(serviceInfos == null || serviceInfos.size() == 0){
                throw new Exception("远程服务不存在！");
            }

            //选择服务
            ServiceInfo serviceInfo = serviceInfos.get(random.nextInt(serviceInfos.size()));

            //构造Request对象
            RpcRequest req = new RpcRequest();
            req.setServiceName(serviceInfo.getName());
            req.setMethodName(method.getName());
            req.setParameterTypes(method.getParameterTypes());
            req.setParameters(args);

            //编组协议
            MessageProtocol messageProtocol = supportMessageProtocol.get(serviceInfo.getProtocol());
            //使用协议编组请求
            byte[] data = messageProtocol.marshallingRequest(req);

            byte[] responseData = netClient.sendRequest(data, serviceInfo);

            RpcResponse response = messageProtocol.unmarshallingResponse(responseData);

            if (response.getException() != null) {
                throw response.getException();
            }

            return response.getReturnValue();
        }
    }
}
