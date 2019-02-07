package com.system.service;

import com.system.pojo.UserCustom;
import com.system.pojo.UserLogin;

import java.util.List;
import java.util.Set;

public interface UserLoginService {

    //根据用户名查找用户
    UserLogin findByName(String username) throws Exception;
    //保存用户登陆信息
    void create(UserLogin user) throws Exception;
    //根据姓名删除
    void deleteByName(String username) throws Exception;
    //根据用户名进行更新
    void updateByName(UserLogin user) throws Exception;
    //获取用户总数
    Integer getUsersCount()throws Exception;
    //根据页码返回用户集
    List<UserLogin> getUsersByPaging(Integer toPageNo)throws Exception;
    //依据用户ID查询用户
    UserLogin getUserByID(Integer uid)throws Exception;


    //依据用户名获取用户角色集
    Set<String> findRoles(String username) throws Exception;
    //依据用户名获取用户权限集
    Set<String> findPermissions(String username) throws Exception;
    //依据用户名获取用户
    UserLogin findOne(String username)throws Exception;
    //查找所属组下的所有用户
    List<UserLogin> findByOrganization(String username)throws Exception;
    //依据用户名获取用户 前端返回类
    UserCustom findOneCustom(String username)throws Exception;
    //获取Userlogin的前端返回类
    List<UserCustom> getUserCustoms(List<UserLogin> users)throws Exception;

}
