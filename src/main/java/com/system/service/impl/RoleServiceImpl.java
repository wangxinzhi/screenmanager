package com.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.system.Utils.RoleUtil;
import com.system.mapper.ResourceMapper;
import com.system.mapper.RoleMapper;
import com.system.pojo.Role;
import com.system.pojo.RoleByFrontFormat;
import com.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    //accept
    @Override
    public Role findByid(Integer id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    //accept
    @Override
    public List<Role> getRoles() throws Exception {
        return roleMapper.getRoles();
    }

    @Override
    public void create(Role role) throws Exception {
        roleMapper.create(role);
    }

    @Override
    public void delete(Integer id) throws Exception {
        roleMapper.delete(id);
    }

    @Override
    public void update(Role role) throws Exception {
        roleMapper.update(role);
    }

    //accept
    @Override
    public Set<String> findRoles(Integer... roleIds) throws Exception {
        Set<String> roles=new HashSet<String>();
        for (Integer id : roleIds){
            Role role=findByid(id);
            if (role!=null){
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Integer... roleIds) throws Exception {
        List<Integer> resourceIds=new ArrayList<>();
        for (Integer id : roleIds){
            Role role=findByid(id);
            if (role!=null){
                resourceIds.addAll(RoleUtil.getResourceIdsByIntegerList(role.getResource_ids()));
            }
        }
        Set<String> permissions=new HashSet<>();
        for (Integer i:resourceIds) {
            permissions.add(resourceMapper.findOnePermission(i));
        }
        return permissions;
    }

    @Override
    public String getRolesName(String roles_id) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String[] ids = roles_id.split(",");
        if (ids != null && ids.length > 0) {
            for (int i= 0; i < ids.length; ++i) {
                stringBuilder.append(findByid(Integer.valueOf(ids[i])).getDescription());
                stringBuilder.append(",");
            }
        }else {
            stringBuilder.append(findByid(Integer.valueOf(roles_id)).getDescription());
        }
        return stringBuilder.toString();
    }

    /**
     * 获取系统全部的 roles (用于前端API)
     * @return
     * @throws Exception
     */
    @Override
    public List<RoleByFrontFormat> findRolesForFrontDesk() throws Exception {
        // 系统全部的 roles
        List<RoleByFrontFormat> roleByFrontFormatList = new ArrayList<>();
        List<Role> roleList = roleMapper.getRoles();
        for (int i = 0; i < roleList.size(); ++i){
            RoleByFrontFormat roleByFrontFormat = new RoleByFrontFormat();
            roleByFrontFormat.setId(roleList.get(i).getId());
            roleByFrontFormat.setValue(roleList.get(i).getRole());
            roleByFrontFormat.setLabel(roleList.get(i).getDescription());
            roleByFrontFormat.setResourceid(roleList.get(i).getResource_ids());
            roleByFrontFormat.setState(roleList.get(i).getAvailable());
            roleByFrontFormatList.add(roleByFrontFormat);
        }
        return roleByFrontFormatList;
    }

    @Override
    public void addRole(JSONObject roleObject) throws Exception {
        Integer id = getMaxSysRoleID() + 1;
        String rolename = roleObject.getString("value");
        String resource = roleObject.getString("resource");
        String description = roleObject.getString("label");
        Boolean state = roleObject.getBoolean("state");
        Role role = new Role();
        role.setId(id);
        role.setRole(rolename);
        role.setResource_ids(resource);
        role.setDescription(description);
        role.setAvailable(state);
        roleMapper.create(role);
    }

    @Override
    public void editRole(JSONObject roleObject) throws Exception {
        Integer id = roleObject.getInteger("id");
        String rolename = roleObject.getString("value");
        String resource = roleObject.getString("resource");
        String description = roleObject.getString("label");
        Boolean state = roleObject.getBoolean("state");
        Role role = new Role();
        role.setId(id);
        role.setRole(rolename);
        role.setResource_ids(resource);
        role.setDescription(description);
        role.setAvailable(state);
        roleMapper.update(role);
    }

    @Override
    public Integer getMaxSysRoleID() throws Exception {
        int maxValue = roleMapper.findMax();
        return maxValue;
    }


}
