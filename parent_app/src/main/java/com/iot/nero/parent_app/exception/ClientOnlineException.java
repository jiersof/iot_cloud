package com.iot.nero.parent_app.exception;

import com.iot.nero.exception.BaseException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2017/6/30
 * Time   上午10:40
 */
public class ClientOnlineException extends BaseException {
    public ClientOnlineException(String message) {
        super(message);
    }

    public ClientOnlineException(String message, Throwable cause) {
        super(message, cause);
    }
}
