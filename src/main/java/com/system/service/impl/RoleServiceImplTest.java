package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.pojo.Role;
import com.system.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-dao.xml"})
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void findByid() throws Exception {
        Role result=roleService.findByid(1);
        Log4jUtil.loggerInfo(result.toString());
    }

    @Test
    public void getRoles() throws Exception {
        List<Role> result = roleService.getRoles();
        Log4jUtil.loggerInfo(new Integer(result.size()).toString());
        for (Role role:result) {
            Log4jUtil.loggerInfo(role.toString());
        }
    }

    @Test
    public void create()throws Exception{
        Role role=new Role();
        role.setId(3);
        role.setRole("test");
        role.setDescription("测试");
        role.setResource_ids("11,12");
        role.setAvailable(true);
        roleService.create(role);
    }

    @Test
    public void delete() throws Exception {
        roleService.delete(3);
    }

    @Test
    public void update() throws Exception {
        Role role=new Role();
        role.setId(3);
        role.setRole("test");
        role.setDescription("测试2");
        role.setResource_ids("11,12");
        role.setAvailable(true);
        roleService.update(role);
    }

    @Test
    public void findRoles() throws Exception {
        Set<String> result = roleService.findRoles(1);
        for (String i:result) {
            Log4jUtil.loggerInfo(i);
        }
    }

    @Test
    public void findPermissions() throws Exception {
        Set<String> result=roleService.findPermissions(1);
        Log4jUtil.loggerInfo(new Integer(result.size()).toString());
        for (String i: result) {
            Log4jUtil.loggerInfo(i);
        }
    }

}