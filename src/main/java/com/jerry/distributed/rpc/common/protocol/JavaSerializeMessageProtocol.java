package com.jerry.distributed.rpc.common.protocol;

import java.io.*;

/**
 * description: JavaSerializeMessageProtocol <br>
 * date: 2021/1/5 23:16 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/523:16
 * @version: 1.0 <br>
 */
public class JavaSerializeMessageProtocol implements MessageProtocol{

    private byte[] serialize(Object obj) throws Exception{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(obj);

        return bout.toByteArray();
    }

    @Override
    public byte[] marshallingRequest(RpcRequest request) throws Exception {
        return this.serialize(request);
    }

    @Override
    public byte[] marshallingResponse(RpcResponse response) throws Exception {
        return this.serialize(response);
    }

    @Override
    public RpcResponse unmarshallingResponse(byte[] responses) throws Exception {
        ObjectInputStream oinput = new ObjectInputStream(new ByteArrayInputStream(responses));
        return (RpcResponse) oinput.readObject();
    }

    @Override
    public RpcRequest unmarshallingRequest(byte[] request) throws Exception {
        ObjectInputStream oinput = new ObjectInputStream(new ByteArrayInputStream(request));
        return (RpcRequest) oinput.readObject();
    }
}
