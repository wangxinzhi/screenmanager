package com.system.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class RedisServiceImplTest {

    @Test
    public void addTest()throws Exception{
        //redisTemplate.boundValueOps("name").set("light");
        System.out.println("sss");
    }


}