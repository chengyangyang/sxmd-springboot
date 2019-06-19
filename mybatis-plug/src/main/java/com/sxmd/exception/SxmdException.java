package com.sxmd.exception;

/**
 * Description: 自定义异常
 *
 * @author cy
 * @date 2019年06月19日 18:13
 * Version 1.0
 */
public class SxmdException extends RuntimeException {

    public SxmdException() {
    }

    public SxmdException(String message) {
        super(message);
    }

    public SxmdException(String message, Throwable cause) {
        super(message, cause);
    }

    public SxmdException(Throwable cause) {
        super(cause);
    }

}
