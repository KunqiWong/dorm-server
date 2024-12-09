package com.kaiyu.common.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import static com.kaiyu.common.constants.ErrorInfo.Code.FAILED;
import static com.kaiyu.common.constants.ErrorInfo.Code.SUCCESS;
import static com.kaiyu.common.constants.ErrorInfo.Msg.OK;

@Data
@Schema(description = "通用响应结果")
public class R<T> {
    @Schema(description = "业务状态码，200-成功，其它-失败")
    private int code;
    @Schema(description = "响应消息", example = "OK")
    private String msg;
    @Schema(description = "响应数据")
    private T data;


    public static R<Void> ok() {
        return new R<>(SUCCESS, OK, null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(SUCCESS, OK, data);
    }

    public static <T> R<T> error(String msg) {
        return new R<>(FAILED, msg, null);
    }

    public static <T> R<T> error(int code, String msg) {
        return new R<>(code, msg, null);
    }

    public R() {
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean success(){
        return code == SUCCESS;
    }


}
