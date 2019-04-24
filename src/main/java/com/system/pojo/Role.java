package com.system.pojo;

import java.util.List;

/**
 * 角色实体和数据库role表对应
 */
public class Role {
    private Integer id;// 角色id
    private String role;// 角色名
    private String description;// 角色描述,UI界面显示使用
    private String resource_ids;// 拥有的资源
    private Boolean available = Boolean.FALSE;// 是否可用,如果不可用将不会添加给用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResource_ids() {
        return resource_ids;
    }

    public void setResource_ids(String resource_ids) {
        this.resource_ids = resource_ids;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "{id="+id+" role="+role+" description="+description+" resource_ids="+resource_ids+" available="+available+"}";
    }
}
