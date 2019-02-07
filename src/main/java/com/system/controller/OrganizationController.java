package com.system.controller;

import com.system.pojo.Organization;
import com.system.service.OrganizationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/show")
    @RequiresPermissions(value = "organization:view")
    public String showOrganization(Model model)throws Exception{
        model.addAttribute("organizations",organizationService.findAll());
        return "organization/showorganization";
    }

    @RequestMapping("/{orgid}/edit")
    public String edit(@PathVariable(value = "orgid")Integer id,Model model)throws Exception{
        model.addAttribute("organization",organizationService.findOne(id));
        return "organization/editorg";
    }

    @RequestMapping("/{orgid}/edit.do")
    @RequiresPermissions(value = "organization:edit")
    public String editdo(@PathVariable(value = "orgid")Integer id, Organization organization)throws Exception{
        organizationService.update(organization);
        return "redirect:organization/show";
    }

}
