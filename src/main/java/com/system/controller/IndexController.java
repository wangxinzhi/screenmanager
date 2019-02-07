package com.system.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        SecurityUtils.getSubject().getSession().setAttribute("principal",SecurityUtils.getSubject().getPrincipal().toString());
        return "index/index";
    }

}
