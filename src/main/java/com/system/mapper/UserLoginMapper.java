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
    //更新用户
    void updateUser(UserLogin userLogin);
    //
    List<UserLogin> findByOrganizationId(Integer oid);
}
