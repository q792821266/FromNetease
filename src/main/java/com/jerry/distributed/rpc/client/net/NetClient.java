package com.jerry.distributed.rpc.client.net;


import com.jerry.distributed.rpc.discovery.ServiceInfo;

/**
 * description: NetClient <br>
 * date: 2021/1/3 11:01 <br>
 *
 * @author: Jerry <br>
 * @version: 1.0 <br>
 */

public interface NetClient {
    byte[] sendRequest(byte[] data, ServiceInfo serviceInfo) throws Throwable;

}
