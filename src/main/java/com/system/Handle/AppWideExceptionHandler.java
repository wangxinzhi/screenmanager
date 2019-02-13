package com.system.Handle;

import com.system.Utils.DateUtil;
import com.system.Utils.Log4jUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

//应用程序异常处理全局类
//控制器通知类  @ExceptionHandler  @InitBinder  @ModelAttribute  注解标注的方法会运用到整个应用程序的所有带有@RequestMapping注解的方法上
@ControllerAdvice
public class AppWideExceptionHandler {

    /**
     * 处理所有控制器的运行时异常 RuntimeException.class
     * @param exception
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus
    public ModelAndView exceptionHandle(RuntimeException exception){
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }


}
