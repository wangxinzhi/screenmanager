package com.system.filter;

import com.system.Utils.Log4jUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxOption extends FormAuthenticationFilter {

    public AjaxOption() {
        this.setLoginUrl("/login");
        this.setSuccessUrl("/");
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Log4jUtil.loggerInfo("======================== AjaxOption.class; preHandle() ========================");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        if(req.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return super.preHandle(request, response);
    }

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean allowed = super.isAccessAllowed(request, response, mappedValue);
        Log4jUtil.loggerInfo("======================== AjaxOption.class; allowed ========================" + allowed);
        if (!allowed) {
            // 判断请求是否是options请求
            String method = WebUtils.toHttp(request).getMethod();
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                Log4jUtil.loggerInfo("======================== AjaxOption.class; OPTIONS ========================");
                return true;
            }
        }
        return allowed;

    }
}
