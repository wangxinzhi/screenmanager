package com.system.log;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * 登陆日志类，记录登陆用户的各项信息
 */
public class LandingLog {

    /**
     * 获取成功登陆用户的IP地址并在控制台输出相应的日志信息
     * @param httpServletRequest
     */
    public void recordLandersIP(HttpServletRequest httpServletRequest) throws UnknownHostException {
        Logger logger = LoggerFactory.getLogger(LandingLog.class);
        String IP = getIP(httpServletRequest);
        String name = SecurityUtils.getSubject().getPrincipal().toString();
        logger.info("Lander's name is {}. Lander's IP is [ {} ]",name,IP);
    }

    /**
     * 获取登陆用户的真实IP地址
     * @param httpServletRequest
     * @return
     * @throws UnknownHostException
     */
    public static String getIP(HttpServletRequest httpServletRequest) throws UnknownHostException {
        String IP = httpServletRequest.getHeader("x-forwarded-for");
        if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)){
            IP = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)){
            IP = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (IP == null || IP.length() == 0 || "unknown".equalsIgnoreCase(IP)){
            IP = httpServletRequest.getRemoteAddr();
            if (IP.equals("127.0.0.1") || IP.equals("0:0:0:0:0:0:0:1")){
                InetAddress inetAddress = null;
                inetAddress = InetAddress.getLocalHost();
                IP = inetAddress.getHostAddress();
            }
        }
        if (IP != null && IP.length() > 15){
            if (IP.indexOf(",") > 0){
                IP = IP.substring(0,IP.indexOf(","));
            }
        }
        return IP;
    }

}
