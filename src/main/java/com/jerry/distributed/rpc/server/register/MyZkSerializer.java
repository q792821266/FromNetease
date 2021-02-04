package com.jerry.distributed.rpc.server.register;

import lombok.SneakyThrows;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * description: MyZkSerializer <br>
 * date: 2021/1/4 17:13 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/417:13
 * @version: 1.0 <br>
 */
public class MyZkSerializer implements ZkSerializer {

    String charset = "UTF-8";

    @SneakyThrows(UnsupportedEncodingException.class)
    public byte[] serialize(Object o) throws ZkMarshallingError {
        return String.valueOf(o).getBytes(charset);
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        return new String(bytes, charset);
    }
}
