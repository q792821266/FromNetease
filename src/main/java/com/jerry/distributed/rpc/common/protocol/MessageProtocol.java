package com.jerry.distributed.rpc.common.protocol;

/**
 * description: MessageProtocol <br>
 * date: 2021/1/4 22:15 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/422:15
 * @version: 1.0 <br>
 */
public interface MessageProtocol {

    /**
     * 传输编码请求
     * @param request
     * @return
     * @throws Exception
     */
    byte[] marshallingRequest(RpcRequest request) throws Exception;

    /**
     * 传输编码响应
     * @param response，
     * @return
     * @throws Exception
     */
    byte[] marshallingResponse(RpcResponse response) throws Exception;

    /**
     * 编码响应字节转换为对象
     * @param responses
     * @return
     * @throws Exception
     */
    RpcResponse unmarshallingResponse(byte[] responses) throws Exception;

    /**
     * 编码请求字节为请求对象
     * @param request
     * @return
     * @throws Exception
     */
    RpcRequest unmarshallingRequest(byte[] request) throws Exception;


}
