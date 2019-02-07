package com.system.service;

import com.system.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    //根据用户id查询角色
    Role findByid(Integer id)throws Exception;

    //获取系统的全部角色
    List<Role> getRoles()throws Exception;

    //添加新角色
    void create(Role role)throws Exception;

    //删除角色
    void delete(Integer id)throws Exception;

    //更新角色信息
    void update(Role role)throws Exception;

    //根据角色id集合获取角色名集合
    Set<String> findRoles(Integer... roleIds)throws Exception;

    //获取权限集合
    Set<String> findPermissions(Integer... roleIds)throws Exception;
}
