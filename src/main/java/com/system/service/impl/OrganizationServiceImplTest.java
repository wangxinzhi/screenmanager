package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.pojo.Organization;
import com.system.service.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class OrganizationServiceImplTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void findOne() throws Exception {
        Log4jUtil.loggerInfo(organizationService.findOne(1).toString());
    }

    @Test
    public void findAll() throws Exception {
        for (Organization o:
             organizationService.findAll()) {
            Log4jUtil.loggerInfo(o.toString());
        }
        Log4jUtil.loggerInfo(new Integer(organizationService.findAll().size()).toString());
    }

    @Test
    public void create() throws Exception {
        Organization organization=new Organization();
        organization.setId(4);
        organization.setName("testCreate");
        organization.setAvailable(true);
        organizationService.create(organization);
    }

    @Test
    public void delete() throws Exception {
        organizationService.delete(4);
    }

    @Test
    public void update() throws Exception {
        Organization organization=new Organization();
        organization.setId(4);
        organization.setName("testUpdate");
        organization.setAvailable(true);
        organizationService.update(organization);
    }

}