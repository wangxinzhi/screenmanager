package com.system.controller;

import com.system.pojo.UploadImageFile;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
//不要
@Controller
public class UploadController {

    @RequestMapping(value = "/uploadImage")
    public ModelAndView upload(HttpServletRequest request,UploadImageFile file)throws Exception{

        String name= RandomStringUtils.randomAlphabetic(10);
        String newFileName=name+".jpg";
        File newFile=new File(request.getServletContext().getRealPath("/image"),newFileName);
        newFile.getParentFile().mkdirs();
        file.getFile().transferTo(newFile);

        ModelAndView modelAndView=new ModelAndView("demo/showUploadedFile");
        modelAndView.addObject("imageName",newFileName);
        modelAndView.addObject("imageRealPath",request.getServletContext().getRealPath("/image"));
        return modelAndView;
    }

    @RequestMapping(value = "/upload")
    public String jump(){
        return "demo/upload";
    }
}
