package com.kaiyu.common.exceptions.impl;

import lombok.Getter;
import com.kaiyu.common.exceptions.CommonException;

@Getter
public class ForbiddenException extends CommonException {
    private final int status = 403;

    public ForbiddenException(String message) {
        super(403, message);
    }

    public ForbiddenException(int code, String message) {
        super(code, message);
    }

    public ForbiddenException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
