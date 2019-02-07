package com.system.controller;

import com.system.pojo.Role;
import com.system.service.ResourceService;
import com.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/show")
    @RequiresPermissions(value = "role:view")
    public String showRole(Model model)throws Exception{
        model.addAttribute("roles",roleService.getRoles());
        return "role/showrole";
    }

    @RequestMapping("/{roleid}/edit")
    @RequiresPermissions(value = "role:update")
    public String editRole(@PathVariable(value = "roleid") Integer id,Model model)throws Exception{
        model.addAttribute("role",roleService.findByid(id));
        model.addAttribute("resource_ids",resourceService.findAll());
        return "role/editrole";
    }

    @RequestMapping("/{roleid}/edit.do")
    @RequiresPermissions(value = "role:update")
    public String editRoledo(@PathVariable(value = "roleid") Integer id, Role role)throws Exception{
        roleService.update(role);
        return "redirect:role/show";
    }

    @RequestMapping("/{roleid}/delete.do")
    @RequiresPermissions(value = "role:delete")
    public String deleteRoledo(@PathVariable(value = "roleid") Integer id)throws Exception{
        roleService.delete(id);
        return "redirect:role/show";
    }

    @RequestMapping("/create")
    @RequiresPermissions(value = "role:create")
    public String createRole(Model model)throws Exception{
        model.addAttribute("resources",resourceService.findAll());
        return "role/createrole";
    }

    @RequestMapping("/create.do")
    @RequiresPermissions(value = "role:create")
    public String createRoledo(Role role)throws Exception{
        roleService.create(role);
        return "redirect:role/show";
    }


}
