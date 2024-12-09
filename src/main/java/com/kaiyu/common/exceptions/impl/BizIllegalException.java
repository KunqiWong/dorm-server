package com.kaiyu.common.exceptions.impl;

import com.kaiyu.common.exceptions.CommonException;

/**
 * 业务不合法异常
 **/
public class BizIllegalException extends CommonException {
    public BizIllegalException(String message) {
        super(message);
    }

    public BizIllegalException(int code, String message) {
        super(code, message);
    }

    public BizIllegalException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
