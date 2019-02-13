package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.mapper.ProgramMapper;
import com.system.pojo.MyPriorityQueue;
import com.system.pojo.Program;
import com.system.pojo.ProgramCustom;
import com.system.pojo.ProgramItem;
import com.system.service.ProgramService;
import com.system.service.UserLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class ProgramServiceImplTest {

    @Autowired
    private ProgramService programService;
    @Autowired
    private ProgramMapper programMapper;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    MyPriorityQueue myPriorityQueue;

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

    @Test
    public void programsConvertToProgramCustoms()throws Exception{
        List<Program> programs = programService.findProgramByUsersNotPassing(userLoginService.findByOrganization("admin"));
        for (Program p:programs) {
            Log4jUtil.loggerInfo(p.toString());
        }

        List<ProgramCustom> programCustoms = programService.programsConvertToProgramCustom(programs);
        for (ProgramCustom p:programCustoms) {
            Log4jUtil.loggerInfo(p.toString() + "--" + p.getPTypeStr() + "--" + p.getPJudgeStr());
        }
    }

    /**
     * 用于测试自定义的多条件优先级队列的排序是否有正确
     * @throws Exception
     */
    @Test
    public void MyPriorityQueueTest()throws Exception{
        ProgramItem p1 = new ProgramItem(3, programService.getProgramByPID(Integer.valueOf(1)));
        ProgramItem p2 = new ProgramItem(5, programService.getProgramByPID(Integer.valueOf(2)));
        ProgramItem p3 = new ProgramItem(1, programService.getProgramByPID(Integer.valueOf(3)));
        ProgramItem p6 = new ProgramItem(3, programService.getProgramByPID(Integer.valueOf(8)));
        ProgramItem p4 = new ProgramItem(3, programService.getProgramByPID(Integer.valueOf(7)));
        ProgramItem p5 = new ProgramItem(6, programService.getProgramByPID(Integer.valueOf(6)));
        myPriorityQueue.add(p1);
        myPriorityQueue.add(p2);
        myPriorityQueue.add(p3);
        myPriorityQueue.add(p4);
        myPriorityQueue.add(p5);
        myPriorityQueue.add(p6);
        int len = myPriorityQueue.size();
        for (int i = 0; i < len; ++i){
            Log4jUtil.loggerInfo(myPriorityQueue.poll().toString());
        }
    }

    /**
     * 用于测试向mysql数据库插入program(未设置主键ID)，是否会依据自增主键自动填充program.ID
     */
    @Test
    public void selfIncreasingPrimaryKeyTest(){
        Program program = new Program();
        program.setPName("test");
        program.setPContent("test");
        program.setScreensList("1,2,4");
        StringBuilder preBulider = new StringBuilder();
        preBulider.append("[ Pre: ");
        preBulider.append(program.toString());
        Log4jUtil.loggerInfo(preBulider.toString());
        programMapper.save(program);//保存节目信息到 programlist 表
        StringBuilder afterBuilder = new StringBuilder();
        afterBuilder.append("[ After: ");
        afterBuilder.append(program.toString());
        Log4jUtil.loggerInfo(afterBuilder.toString());
    }
}