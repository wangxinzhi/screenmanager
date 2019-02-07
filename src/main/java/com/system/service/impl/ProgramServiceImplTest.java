package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.mapper.ProgramMapper;
import com.system.pojo.Program;
import com.system.service.ProgramService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class ProgramServiceImplTest {

    @Autowired
    private ProgramService programService;
    @Autowired
    private ProgramMapper programMapper;

    @Test
    public void saveProgram() throws Exception {
    }

    @Test
    public void updateProgram() throws Exception {
    }

    @Test
    public void deleteProgram() throws Exception {
    }

    @Test
    public void getProgramCount() throws Exception {
    }

    @Test
    public void getProgramsByPaging() throws Exception {
    }

    @Test
    public void getProgramByPID() throws Exception {
    }

    @Test
    public void findProgramBySender() throws Exception {
        int i=0;
        for (Program p :
             programService.findProgramBySender("admin")
            ) {
            Log4jUtil.loggerInfo(p.toString());
            i++;
        }
        Log4jUtil.loggerInfo(Integer.toString(i));
    }

    @Test
    public void updateByCheck() throws Exception {
    }

    @Test
    public void findProgramByUsers() throws Exception {
    }

    @Test
    public void findProgramByUsersNotPassing() throws Exception {
    }

    @Test
    public void readAllFiles() throws Exception {
    }

    @Test
    public void getFilePath() throws Exception {
    }

    @Test
    public void getFeedback()throws Exception{
        String result=programService.getFeedback(8);
        Log4jUtil.loggerInfo("[feedback="+result+"-]");
    }

    @Test
    public void getTimeSchedule()throws Exception{
        List<Program>test=programService.getTimeSchedule();
        for (Program p:
             test) {
            Log4jUtil.loggerInfo("[节目id"+p.getID()+"  开始时间="+p.formatBeginTime()+"  结束时间="+p.formatEndTime()+"]");
        }
    }
}