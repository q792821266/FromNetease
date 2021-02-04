package com.jerry.distributed.rpc.server.register;

import lombok.*;

/**
 * description: ServiceObject <br>
 * date: 2021/1/6 17:55 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/617:55
 * @version: 1.0 <br>
 */
@Getter
@Setter
@Data
public class ServiceObject {

    private String  name;

    private Class<?> interf;

    private Object obj;
}
