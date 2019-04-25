package com.system.service;

import com.alibaba.fastjson.JSONObject;
import com.system.pojo.UserByFrontFormat;
import com.system.pojo.UserLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    // 依据用户名查询该用户所处部门下所有用户的信息 users (用于前端API)
    List<UserByFrontFormat> findUsersForFrontDesk(String username)throws Exception;

    // 依据id删除用户信息
    void deleteUserById(Integer uid)throws Exception;

    // 修改用户信息
    void editUserInfo(JSONObject userObject)throws Exception;

    // 添加新用户
    void addUser(JSONObject userObject)throws Exception;

    //
    int getSysMaxUserID()throws Exception;

    //
    void resetUserPassword(int uid)throws Exception;

    //
    String getUserInfo(String sessionId, HttpServletRequest request, HttpServletResponse response)throws Exception;


}
