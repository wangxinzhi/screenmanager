package com.system.Exception;

//还没什么用 2019/2/9
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(Integer code,String message){
        super(message);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code){
        this.code=code;
    }
}
