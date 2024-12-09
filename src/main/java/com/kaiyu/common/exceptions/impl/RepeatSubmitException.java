package com.kaiyu.common.exceptions.impl;

import com.kaiyu.common.exceptions.CommonException;


public class RepeatSubmitException extends CommonException {
    // private final int status = 68;

    public RepeatSubmitException() {
        super(68, "请勿重复提交");
    }
    public RepeatSubmitException(String message) {
        super(68, message);
    }

    public RepeatSubmitException(int code, String message) {
        super(code, message);
    }

    public RepeatSubmitException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}