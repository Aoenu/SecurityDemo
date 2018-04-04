package com.sec.oauth2;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/4/4.
 * @description
 */
public class NameNotFundException extends RuntimeException{
    public NameNotFundException(String msg, Throwable t) {
        super(msg, t);
    }

    public NameNotFundException(String msg) {
        super(msg);
    }
}
