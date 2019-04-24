package com.system.mapper;

import com.system.pojo.Page;
import com.system.pojo.UserLogin;

import java.util.List;

public interface UserLoginMapper {
    //依据用户名获取用户
    UserLogin getUser(String username);
    //获取用户数量
    Integer getCount();
    //依据页码获取用户集
    List<UserLogin> getUsersByPaging(Page page);
    //依据用户ID查询用户
    UserLogin getUserByID(Integer uid);
    //增加用户
    void createUser(UserLogin users);
    //删除用户
    void deleteUser(String username);
    //删除用户
    void deleteUserById(Integer uid);
    //更新用户
    void updateUser(UserLogin userLogin);
    // 修改用户
    void verifyUserInfo(UserLogin userLogin);
    // 依据部门，获取该部门及下属部门所有的用户
    List<UserLogin> findByOrganizationId(Integer oid);
    // 找到用户表中最大的id
    int findMax();
    // 重置用户密码为123456
    void resetUserPwd(int uid);
}
