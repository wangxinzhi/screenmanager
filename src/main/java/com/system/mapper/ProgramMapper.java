package com.system.mapper;

import com.system.pojo.Page;
import com.system.pojo.Program;
import com.system.pojo.ProgramByCheck;

import java.util.List;

public interface ProgramMapper {
    //保存完整节目
    void save(Program program);
    //更新完整节目
    void update(Program program);
    //获取所有节目
    List<Program> getAll();
    //获取节目ByPaging
    List<Program> getAllByPaging(Page page);
    //获取节目总数
    Integer getCount();
    //通过节目ID获取节目
    Program getProgramByPID(Integer pid);
    //删除节目
    void delete(Integer pid);
    //通过发布人获取所发布的节目信息
    List<Program> findBySender(String username);
    //更新节目
    void updateByCheck(ProgramByCheck programByCheck);
    //更新反馈表 对programbycheck表进行操作
    void updateFeedback(ProgramByCheck programByCheck);
    //获取反馈信息 通过节目id从programbycheck表获取
    String getFeedback(Integer id);
    //审核不通过 对节目数据进行修改
    void updateOfFailedChecking(Program program);
}
