package com.kaiyu.common.exceptions.impl;

import lombok.Getter;
import com.kaiyu.common.exceptions.CommonException;



@Getter
public class CardException extends CommonException {

    private final int status = 600;

    public CardException(String message) {
        super(600, message);
    }

    public CardException(int code, String message) {
        super(code, message);
    }

    public CardException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

}
