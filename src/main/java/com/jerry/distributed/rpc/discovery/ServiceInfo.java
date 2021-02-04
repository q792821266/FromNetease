package com.jerry.distributed.rpc.discovery;

import lombok.Data;

/**
 * description: ServiceInfo <br>
 * date: 2021/1/3 15:52 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/315:52
 * @version: 1.0 <br>
 */
@Data
public class ServiceInfo {
    private String name;
    private String protocol;
    private String address;


}
