package com.system.controller;

import com.system.Utils.Log4jUtil;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    //登陆跳转
    /*@RequestMapping(value = "/login",method = {RequestMethod.GET})
    public String loginUI(){
        System.out.println("loginUI");
        return "/login.jsp";
    }*/

    //登陆表单处理
    /*@RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(HttpServletRequest request,HttpServletResponse response)throws Handle{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("login");
        if (userLoginService.findByName(username).getPassword().equals(password)){
            System.out.println("success......");
            return "success";
        }
        else {
            //登陆失败，用户名或密码为空
            System.out.println("failed.....");
            return "/login.jsp";
        }
    }*/

    //Shiro 实现登陆失败处理
    @RequestMapping( value = "/login")
    public String LoginByShiro(HttpServletRequest request, Model model)throws Exception{
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error=null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)){
            error="用户名/密码错误";
        }else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
            error="用户名/密码错误";
        }else if (exceptionClassName!=null){
            error="其他错误";
            //Log4jUtil.logger.info("其他错误:"+exceptionClassName);
        }
        model.addAttribute("loginError",error);
        return "/../login";
    }

}
