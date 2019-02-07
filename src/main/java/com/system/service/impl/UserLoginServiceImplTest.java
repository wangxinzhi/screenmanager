package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.pojo.UserLogin;
import com.system.service.UserLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class UserLoginServiceImplTest {

    @Autowired
    private UserLoginService userLoginService;

    @Test
    public void findByName()throws Exception{
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-dao.xml"});
        UserLoginService userLoginService= (UserLoginService) context.getBean("userLoginServiceImpl");
        UserLogin result = userLoginService.findByName("admin");
        Log4jUtil.loggerInfo(result.toString());
    }

    @Test
    public void create()throws Exception{
        UserLogin user=new UserLogin();
        user.setOrganization_id(2);
        user.setUsername("test");
        user.setPassword("test");
        user.setRole_ids("3");
        user.setLocked(false);
        userLoginService.create(user);
    }

    @Test
    public void delete()throws Exception{
        userLoginService.deleteByName("test");
    }

    @Test
    public void update()throws Exception{
        UserLogin user=new UserLogin();
        user.setOrganization_id(2);
        user.setUsername("test");
        user.setPassword("test111");
        user.setRole_ids("4");
        user.setLocked(false);
        userLoginService.updateByName(user);
    }

    @Test
    public void findRoles() throws Exception {
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-dao.xml"});
        UserLoginService userLoginService=(UserLoginService)context.getBean("userLoginServiceImpl");
        Set<String> result = userLoginService.findRoles("admin");
        Log4jUtil.loggerInfo(new Integer(result.size()).toString());
        for (String i:result) {
            Log4jUtil.loggerInfo(i);
        }
    }

    @Test
    public void findPermissions() throws Exception {
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-dao.xml"});
        UserLoginService userLoginService=(UserLoginService)context.getBean("userLoginServiceImpl");
        Set<String> result=userLoginService.findPermissions("teacher");
        Log4jUtil.loggerInfo(new Integer(result.size()).toString());
        for (String per:result) {
            Log4jUtil.loggerInfo(per);
        }
    }

    @Test
    public void findByOrganization() throws Exception{
        for (UserLogin user:userLoginService.findByOrganization("admin")) {
            Log4jUtil.loggerInfo(user.toString());
        }
    }

}