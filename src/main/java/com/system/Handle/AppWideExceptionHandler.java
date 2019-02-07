package com.system.Handle;

import com.system.Utils.DateUtil;
import com.system.Utils.Log4jUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//应用程序异常处理全局类
//控制器通知类  @ExceptionHandler  @InitBinder  @ModelAttribute  注解标注的方法会运用到整个应用程序的所有带有@RequestMapping注解的方法上
@ControllerAdvice
public class AppWideExceptionHandler {

    Log4jUtil log4jUtil;

    /**
     * 处理所有控制器的 Exception.class 类型异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandle(Exception e){
        DateUtil dateUtil = new DateUtil();
        String causeMsg = e.getCause().getMessage();
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("Result","异常");
        log4jUtil.writeIntoText(causeMsg, dateUtil.getDateNowStr());
        return modelAndView;
    }

}
