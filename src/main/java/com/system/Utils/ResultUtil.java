package com.system.Utils;

import com.system.pojo.Result;


/**
 *
 */
public class ResultUtil {

    public static Result error(Integer code,String message){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}
