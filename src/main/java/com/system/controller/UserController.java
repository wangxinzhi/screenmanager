package com.system.controller;

import com.system.pojo.UserCustom;
import com.system.pojo.UserLogin;
import com.system.service.OrganizationService;
import com.system.service.RoleService;
import com.system.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/{principal}/showdetails")
    public String show(@PathVariable(value = "principal") String username, Model model)throws Exception{
        //UserLogin user=userLoginService.findOne(username);
        //model.addAttribute("user",user);
        UserCustom userCustom=userLoginService.findOneCustom(username);
        model.addAttribute("user",userCustom);
        return "user/showdetails";
    }

    @RequestMapping("/{principal}/showuser")
    @RequiresPermissions(value = "user:view")
    public String showUser(@PathVariable(value = "principal") String username,Model model)throws Exception{
        List<UserCustom> userCustoms=userLoginService.getUserCustoms(userLoginService.findByOrganization(username));
        model.addAttribute("users",userCustoms);
        return "user/showuser";
    }

    @RequestMapping("/{username}/edit")
    @RequiresPermissions(value = "user:update")
    public String editUser(@PathVariable(value = "username") String username,Model model)throws Exception{
        model.addAttribute("user",userLoginService.findByName(username));
        model.addAttribute("organizations",organizationService.findAll());
        model.addAttribute("roles",roleService.getRoles());
        return "user/edituser";
    }

    @RequestMapping("/{username}/edit.do")
    @RequiresPermissions(value = "user:update")
    public String editUserdo(@PathVariable(value = "username") String username, UserLogin user, HttpServletRequest request)throws Exception{
        userLoginService.updateByName(user);
        return "redirect:"+ SecurityUtils.getSubject().getSession().getAttribute("principal")+"/showuser";
    }

    @RequestMapping("/{username}/delete.do")
    @RequiresPermissions(value = "user:delete")
    public String deleteUserdo(@PathVariable(value = "username") String username)throws Exception{
        userLoginService.deleteByName(username);
        return "redirect:"+ SecurityUtils.getSubject().getSession().getAttribute("principal")+"/showuser";
    }

    @RequestMapping("/create")
    @RequiresPermissions(value = "user:create")
    public String createUser(Model model)throws Exception{
        model.addAttribute("organizations",organizationService.findAll());
        model.addAttribute("roles",roleService.getRoles());
        return "user/createuser";
    }

    @RequestMapping("/create.do")
    @RequiresPermissions(value = "user:create")
    public String createUserdo(UserLogin user)throws Exception{
        userLoginService.create(user);
        return "redirect:"+ SecurityUtils.getSubject().getSession().getAttribute("principal")+"/showuser";
    }
}
