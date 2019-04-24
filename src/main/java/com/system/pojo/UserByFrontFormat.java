package com.system.pojo;

/***
 * User的前端格式
 */
public class UserByFrontFormat {

    private Integer id;// 用户ID
    private String organization;// 所属部门
    private String name;// 用户名
    private String roles;// 角色集
    private boolean locking;// 是否锁定

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isLocking() {
        return locking;
    }

    public void setLocking(boolean locking) {
        this.locking = locking;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("====== UserByFrontFormat.class ====== ");
        stringBuilder.append(getId());
        stringBuilder.append(" === ");
        stringBuilder.append(getName());
        stringBuilder.append(" === ");
        stringBuilder.append(getOrganization());
        stringBuilder.append(" === ");
        stringBuilder.append(getRoles());
        return stringBuilder.toString();
    }
}
