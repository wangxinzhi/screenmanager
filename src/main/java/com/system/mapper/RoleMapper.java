package com.system.mapper;

import com.system.pojo.Role;

import java.util.List;

public interface RoleMapper {
    //更具用户id查询用户角色
    Role selectByPrimaryKey(Integer roleid);
    //获取系统全部角色信息
    List<Role> getRoles();
    //添加角色
    void create(Role role);
    //删除角色
    void delete(Integer id);
    //更新角色
    void update(Role role);
    //获取优先级
    int findPriorityByRole(Integer roleId);
}
