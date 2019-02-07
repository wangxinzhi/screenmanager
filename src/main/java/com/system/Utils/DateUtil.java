package com.system.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//日期工具类
public class DateUtil {

    /**
     * 获取格式为 yyyy-MM-dd at hh:mm:ss 的当前时间
     * @return “yyyy-MM-dd at hh:mm:ss” 格式的日期字符串
     */
    public String getDateNowStr(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' hh:mm:ss");
        return simpleDateFormat.format(date);
    }

}
