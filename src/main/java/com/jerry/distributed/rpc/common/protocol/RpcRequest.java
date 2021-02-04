package com.jerry.distributed.rpc.common.protocol;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * description: Request <br>
 * date: 2021/1/4 22:33 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/422:33
 * @version: 1.0 <br>
 */
@Getter
@Setter
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1185953548544150173L;
    private String serviceName;
    private String methodName;
    private Map<String,String> header = new HashMap<String,String>();
    private Class<?>[] parameterTypes;
    private Object[] parameters;


}
