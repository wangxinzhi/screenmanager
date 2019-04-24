package com.system.filter;

import com.system.Utils.Log4jUtil;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * 解决Axios CROS跨域请求问题 配置Filter
 */
public class LoginAuthFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //设置允许多个域名请求
        String[] allowDomains = {"http://127.0.0.1:8082","http://127.0.0.1:8082/screen","http://localhost:8082"};
        Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
        String originHeads = request.getHeader("Origin");
        if (allowOrigins.contains(originHeads)){
            // 设置允许跨域的配置
            // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
            response.setHeader("Access-Control-Allow-Origin", originHeads);
            Log4jUtil.loggerInfo("#################################### have Origin ####################################");
        }

        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token, Origin, X-Requested-With, Content-Type, Accept, mid, X-Token");
        //response.setContentType("application/json;charset=utf-8");
        // 设置服务器允许浏览器发送请求都携带cookie
        // response.setHeader("Access-Control-Allow-Credentials","true");
        /*if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
