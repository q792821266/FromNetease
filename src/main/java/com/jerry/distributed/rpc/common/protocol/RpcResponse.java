package com.jerry.distributed.rpc.common.protocol;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * description: RpcReponse <br>
 * date: 2021/1/5 21:12 <br>
 *
 * @author: Jerry <br>
 * @Date: 2021/1/521:12
 * @version: 1.0 <br>
 */
@NoArgsConstructor
@Getter
@Setter
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = -8792699952884873531L;
    private String status;

    private Map<String,String> headers = new HashMap<String,String>();
    private Object returnValue;
    private Exception exception;

    public RpcResponse(String status) {
        this.status = status;
    }
}
