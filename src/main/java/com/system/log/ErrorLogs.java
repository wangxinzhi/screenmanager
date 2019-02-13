package com.system.log;


import com.sun.org.apache.xpath.internal.operations.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 记录系统中所有出现的ERROR
 */
public class ErrorLogs {

    /**
     * 获取错误信息，并写入error.log
     * @param
     */
    public void getMsg(RuntimeException exception){
        Logger logger = LoggerFactory.getLogger(ErrorLogs.class);
        logger.error("Exception =>",exception);
    }

}
