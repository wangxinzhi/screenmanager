package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.Utils.RoleUtil;
import com.system.mapper.OrganizationMapper;
import com.system.mapper.UserLoginMapper;
import com.system.pojo.*;
import com.system.service.OrganizationService;
import com.system.service.RoleService;
import com.system.service.UserLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    public UserLoginMapper userLoginMapper;

    @Autowired
    public RoleService roleService;

    @Autowired
    public OrganizationMapper organizationMapper;

    //accept
    public UserLogin findByName(String username)throws Exception{
        UserLogin userLogin = userLoginMapper.getUser(username);
        return userLogin;
    }

    public void create(UserLogin user)throws Exception{
        userLoginMapper.createUser(user);
    }

    public void deleteByName(String username)throws Exception{
        userLoginMapper.deleteUser(username);
    }

    public void updateByName(UserLogin user)throws Exception{
        userLoginMapper.updateUser(user);
    }

    //accept
    @Override
    public Integer getUsersCount() throws Exception {
        return userLoginMapper.getCount();
    }

    @Override
    public List<UserLogin> getUsersByPaging(Integer toPageNo) throws Exception {
        Page page=new Page();
        page.setToPageNo(toPageNo);
        List<UserLogin> list=userLoginMapper.getUsersByPaging(page);
        return list;
    }

    //accept
    @Override
    public UserLogin getUserByID(Integer uid) throws Exception {
        return userLoginMapper.getUserByID(uid);
    }

    @Override
    public Set<String> findRoles(String username) throws Exception {
        UserLogin user = findByName(username);
        if (user==null){
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(RoleUtil.getRoleIdsByIntegerList(user.getRole_ids()).toArray(new Integer[0]));
    }

    @Override
    public Set<String> findPermissions(String username) throws Exception {
        UserLogin user=findByName(username);
        if (user==null){
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(RoleUtil.getRoleIdsByIntegerList(user.getRole_ids()).toArray(new Integer[0]));
    }

    @Override
    public UserLogin findOne(String username) throws Exception {
        return userLoginMapper.getUser(username);
    }

    @Override
    public List<UserLogin> findByOrganization(String username) throws Exception {
        UserLogin user=userLoginMapper.getUser(username);
        String parentStr=organizationMapper.findOne(user.getOrganization_id()).getParent_ids()+user.getOrganization_id();
        List<Organization> list = organizationMapper.findAll();
        List<Integer> organizationIds=new ArrayList<>();
        List<UserLogin> result=new ArrayList<>();
        if (list.size()>0&&list!=null){
            for (Organization o:list) {
                if (o.getParent_ids().split(parentStr).length>1){
                    organizationIds.add(o.getId());
                }
            }
        }
        organizationIds.add(user.getOrganization_id());
        if (organizationIds.size()>0){
            for (Integer i:organizationIds) {
                result.addAll(userLoginMapper.findByOrganizationId(i));
            }
        }
        return result;
    }

    @Override
    public UserCustom findOneCustom(String username) throws Exception {
        UserLogin userLogin=userLoginMapper.getUser(username);//用户信息
        Organization organization= organizationMapper.findOne(userLogin.getOrganization_id());//部门信息
        List<Role> roles=new ArrayList<>();//角色集
        String rolestr=userLogin.getRole_ids();
        for (String r:
             rolestr.split(",")) {
            roles.add(roleService.findByid(Integer.parseInt(r)));
        }
        UserCustom userCustom=new UserCustom(userLogin,organization,roles);
        Log4jUtil.loggerInfo("OrganizationID="+userCustom.getOrganization().getId()+"  OrganizationName="+userCustom.getOrganization().getName());
        return userCustom;
    }

    @Override
    public List<UserCustom> getUserCustoms(List<UserLogin> users) throws Exception {
        List<UserCustom> userCustoms=new ArrayList<>();
        for (UserLogin user:
             users) {
            Organization organization=organizationMapper.findOne(user.getOrganization_id());
            UserCustom userCustom=new UserCustom();
            userCustom.setUserLogin(user);
            userCustom.setOrganization(organization);
            userCustoms.add(userCustom);
        }
        for (UserCustom user:
             userCustoms) {
            Log4jUtil.loggerInfo("UserName="+user.getUserLogin().getUsername()+"  Organization="+user.getOrganization().getName());
        }
        return userCustoms;
    }
}
