package com.system.Handle;

import com.system.Exception.UserException;
import com.system.Utils.ResultUtil;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//舍弃
public class ExceptionHandle implements HandlerExceptionResolver{

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandle.class);

    /*用@ControllerAdvice有问题
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ModelAndView exceptionHandle(Exception e, ModelAndView modelAndView){
        if (e instanceof UnknownAccountException){
            UserException userException = (UserException) e;
            modelAndView.addObject("Result",ResultUtil.error(userException.getCode(),userException.getMessage()));
            modelAndView.setViewName("error");
            return modelAndView;
        }
        else {
            logger.error("系统异常={}",e);
            modelAndView.addObject("Result",ResultUtil.error(-1,"未知错误"));
            modelAndView.setViewName("error");
            System.out.println("-----ExceptionHandle-----");
            return modelAndView;
        }
    }*/

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof UnknownAccountException){
            UserException userException = (UserException) e;
            modelAndView.addObject("Result", ResultUtil.error(userException.getCode(), userException.getMessage()));
            modelAndView.setViewName("error");
            return modelAndView;
        }
        else if (e instanceof UnauthorizedException){
            modelAndView.addObject("Result","无权限");
            modelAndView.setViewName("error");
            return modelAndView;
        }
        else {
            modelAndView.addObject("Result", ResultUtil.error(-1,"未知错误"));
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }
}
