package com.system.pojo;

import com.sun.xml.internal.ws.server.ServerRtException;

import java.util.List;

//UserLogin类的前端返回类
public class UserCustom {

    private UserLogin userLogin;
    private Organization organization;
    private List<Role> roles;

    public UserCustom(){
    }

    public UserCustom(UserLogin user,Organization org,List<Role> roles){
        this.userLogin=user;
        this.organization=org;
        this.roles=roles;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String rolesname(){
        String rolesname="";
        for (Role r:
             roles) {
            rolesname+=r.getDescription()+",";
        }
        return rolesname;
    }
}
