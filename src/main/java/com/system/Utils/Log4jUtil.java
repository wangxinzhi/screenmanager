package com.system.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

//日志工具类
public class Log4jUtil {

    public static Logger logger= LoggerFactory.getLogger(Log4jUtil.class);

    public static void loggerInfo(String message){
        logger.info(message);
    }

    /**
     * 获取登陆用户的IP地址并在控制台输出相应的日志信息
     * @param name
     * @param httpServletRequest
     */
    public void recordLandersIP(String name, HttpServletRequest httpServletRequest) throws UnknownHostException {
        Logger logger=LoggerFactory.getLogger("console");
        String IP=getIP(httpServletRequest);
        logger.info("Lander's IP is [ {} ]",IP);
    }

    /**
     * 获取登陆用户的真实IP地址
     * @param httpServletRequest
     * @return
     * @throws UnknownHostException
     */
    public static String getIP(HttpServletRequest httpServletRequest) throws UnknownHostException {
        String IP=httpServletRequest.getHeader("x-forwarded-for");
        if (IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)){
            IP=httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)){
            IP=httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (IP==null||IP.length()==0||"unknown".equalsIgnoreCase(IP)){
            IP=httpServletRequest.getRemoteAddr();
            if (IP.equals("127.0.0.1")||IP.equals("0:0:0:0:0:0:0:1")){
                InetAddress inetAddress=null;
                inetAddress=InetAddress.getLocalHost();
                IP=inetAddress.getHostAddress();
            }
        }
        if (IP!=null&&IP.length()>15){
            if (IP.indexOf(",")>0){
                IP=IP.substring(0,IP.indexOf(","));
            }
        }
        return IP;
    }

    /**
     * 异常信息写入日志文件
     * @param msg 异常信息
     * @param dateStr 发生时间
     */
    public void writeIntoText(String msg, String dateStr){
        logger.error("There were some errors which errors' date's = [ {} ] and msg's = [ {} ]",dateStr,msg);
    }
}
