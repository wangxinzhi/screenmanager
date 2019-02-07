package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.mapper.ScreenMapper;
import com.system.pojo.Screen;
import com.system.service.ScreenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class ScreenServiceImplTest {

    @Autowired
    private ScreenService screenService;

    @Autowired
    private ScreenMapper screenMapper;

    @Test
    public void getScreensByOrganizationId() throws Exception {
        Integer id=1;
        //for (Screen s:
        //     screenService.getScreensByOrganizationId(id)) {
        //    Log4jUtil.loggerInfo(s.toString());
        //}
        Log4jUtil.loggerInfo(screenMapper.getScreens(id));
    }

}