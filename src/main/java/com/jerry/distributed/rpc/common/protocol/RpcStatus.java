package com.jerry.distributed.rpc.common.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description rpc请求的响应状态码
 * @Author Jerry
 * @Date 22:04 2021/2/4
 * @Param  * @param null
 * @return
 **/
public enum RpcStatus {
    /**
     * success 请求成功
     * error 请求报错
     * notFound 无法找到对应服务
     */
    SUCCESS(200, "SUCCESS"), ERROR(500, "ERROR"), NOT_FOUND(404, "NOT_FOUND");

    /** 状态描述 */
    private int code;

    /**
     *  详细描述
     */
    private String description;



    RpcStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
