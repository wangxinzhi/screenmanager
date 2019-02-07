package com.system.service.impl;

import com.system.Utils.RoleUtil;
import com.system.mapper.ResourceMapper;
import com.system.mapper.RoleMapper;
import com.system.pojo.Role;
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

}
