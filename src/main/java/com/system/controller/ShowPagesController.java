package com.system.controller;

import com.system.pojo.Category;
import com.system.pojo.Page;
import com.system.service.ShowPagesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.List;
//不要
@Controller
@RequestMapping("/demo")
public class ShowPagesController {

    @Resource(name = "showPagesServiceImpl")
    ShowPagesService showPagesService;


    @RequestMapping("/showPages")
    public ModelAndView showPages(Integer pageNo) throws Exception {
        ModelAndView modelAndView=new ModelAndView("demo/showPage");
        List<Category> list=null;
        int count = showPagesService.Count();
        System.out.println("Count = "+count);
        Page page=new Page();
        if (pageNo==null||pageNo.intValue()<0){
            pageNo=0;
        }
        //page.setStart(pageNo);
        //page.caulLast(count);
        list=showPagesService.getAll(page);
        modelAndView.addObject("cs",list);
        return modelAndView;
    }
}
