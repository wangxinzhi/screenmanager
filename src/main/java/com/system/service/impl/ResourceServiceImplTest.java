package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.mapper.ResourceMapper;
import com.system.pojo.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext-dao.xml"})
public class ResourceServiceImplTest {

    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void findOne() throws Exception {
        Log4jUtil.loggerInfo(resourceMapper.findOne(11).toString());
    }

    @Test
    public void findAll() throws Exception {
        Log4jUtil.loggerInfo(new Integer(resourceMapper.findAll().size()).toString());
    }

    @Test
    public void create() throws Exception {
        Resource resource=new Resource();
        resource.setId(100);
        resource.setName("test");
        resource.setPermission("test:*");
        resource.setAvailable(true);
        resourceMapper.create(resource);
    }

    @Test
    public void delete() throws Exception {
        resourceMapper.delete(100);
    }

    @Test
    public void update() throws Exception {
        Resource resource=new Resource();
        resource.setId(100);
        resource.setName("test2");
        resource.setPermission("test:view");
        resource.setAvailable(true);
        resourceMapper.update(resource);
    }

}