package com.system.pojo;

import java.util.List;

/**
 * 登陆用户实体和数据库userlogin表对应
 */
public class UserLogin {
    private Integer userId;//用户id
    private Integer organization_id;
    private String username;//用户名
    private String password;//用户密码
    private String role_ids;//用户角色集
    private Boolean locked = Boolean.FALSE;//用户是否锁定

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public String getRole_ids() {
        return role_ids;
    }

    public void setRole_ids(String role_ids) {
        this.role_ids = role_ids;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getLocked() {
        return locked;
    }

    @Override
    public String toString() {
        return "{userid="+userId+" organization_id="+organization_id+" username="+username+" role_ids="+role_ids+" locked="+locked+"}";
    }
}
