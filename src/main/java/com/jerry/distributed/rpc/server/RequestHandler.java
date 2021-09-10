package com.jerry.distributed.rpc.server;

import com.jerry.distributed.rpc.common.protocol.MessageProtocol;
import com.jerry.distributed.rpc.common.protocol.RpcRequest;
import com.jerry.distributed.rpc.common.protocol.RpcResponse;
import com.jerry.distributed.rpc.common.protocol.RpcStatus;
import com.jerry.distributed.rpc.server.register.ServiceObject;
import com.jerry.distributed.rpc.server.register.ServiceRegister;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName RequestHandler
 * @Description eee...
 * @Author Jerry
 * @Date 2021/2/4 21:56
 * @Version 1.0
 */
@AllArgsConstructor
@Getter
@Setter
public class RequestHandler {

    private MessageProtocol messageProtocol;
    private ServiceRegister serviceRegister;

    public byte[] handleRequest(byte[] data) throws Exception {
        //1 根据协议解组消息
        RpcRequest request = this.messageProtocol.unmarshallingRequest(data);

        //2.查找服务对象
        ServiceObject serviceObject = this.serviceRegister.getServiceObject(request.getServiceName());

        RpcResponse rpcResponse = null;

        if (serviceObject == null) {
            rpcResponse = new RpcResponse(RpcStatus.NOT_FOUND);
        }else{
            try {
                Method method = serviceObject.getInterf().getMethod(request.getMethodName(), request.getParameterTypes());
                Object returnValue = method.invoke(serviceObject.getObj(), request.getParameters());
                rpcResponse = new RpcResponse(RpcStatus.SUCCESS);
                rpcResponse.setReturnValue(returnValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                rpcResponse = new RpcResponse(RpcStatus.ERROR);
                rpcResponse.setException(e);
            }
        }

        return this.messageProtocol.marshallingResponse(rpcResponse);
    }
}
