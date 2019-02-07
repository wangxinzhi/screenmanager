package com.system.service;

import com.system.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProgramService {

    //保存节目
    void saveProgram(Program program)throws Exception;
    //更新节目
    void updateProgram(Program program)throws Exception;
    //删除节目
    void deleteProgram(Integer pid)throws Exception;
    //获取节目数量
    Integer getProgramCount()throws Exception;
    //获取分页查询节目
    List<Program> getProgramsByPaging(Integer toPageNo)throws Exception;
    //通过节目ID获取节目
    Program getProgramByPID(Integer pid)throws Exception;


    //通过发布人查找节目
    List<Program> findProgramBySender(String username)throws Exception;
    //更新审核情况(对两张表进行更新  分别是:programlist(存储节目信息) programbycheck(存储节目审核的回馈信息))
    void updateByCheck(ProgramByCheck programByCheck)throws Exception;
    //获取用户集的节目列表
    List<Program> findProgramByUsers(List<UserLogin> userLogins)throws Exception;
    //获取用户集未通过审核的节目列表
    List<Program> findProgramByUsersNotPassing(List<UserLogin> userLogins)throws Exception;
    //读取文件下所有文件名
    List<String> readAllFiles(String path)throws Exception;
    //获取完整的资源文件路径集合(eg.: 'zutimg/username/random(5)/filename' )
    List<String> getFilePath(String path,Integer type)throws Exception;
    //获取反馈信息 通过节目id从programbycheck表获取
    String getFeedback(Integer pid)throws Exception;
    //返回节目时间安排表
    List<Program> getTimeSchedule()throws Exception;
    //审核未通过 重新修改节目
    void updateProgramOfFailedChecking(HttpServletRequest request, UploadProgram program)throws Exception;
}
