package com.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.system.Utils.Log4jUtil;
import com.system.mapper.OrganizationMapper;
import com.system.mapper.RoleMapper;
import com.system.mapper.UserLoginMapper;
import com.system.pojo.Organization;
import com.system.pojo.UserByFrontFormat;
import com.system.pojo.UserLogin;
import com.system.service.OrganizationService;
import com.system.service.RoleService;
import com.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserLoginMapper userLoginMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    public RoleService roleService;

    @Autowired
    public OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationService organizationService;

    /**
     * // 依据用户名查询该用户所处部门下所有用户的信息 users (用于前端API)
     * @param username 用户名
     * @return
     * @throws Exception
     */
    @Override
    public List<UserByFrontFormat> findUsersForFrontDesk(String username) throws Exception {
        UserLogin user = userLoginMapper.getUser(username);
        String parentStr = organizationMapper.findOne(user.getOrganization_id()).getParent_ids() + user.getOrganization_id();
        List<Organization> list = organizationMapper.findAll();
        List<Integer> organizationIds = new ArrayList<>();
        List<UserLogin> result = new ArrayList<>();
        if (list.size() > 0 && list != null){
            for (Organization o:list) {
                if (o.getParent_ids().split(parentStr).length > 1){
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

        List<UserByFrontFormat> frontDeskResult = new ArrayList<>();
        for (int i =0; i < result.size(); i++) {
            UserByFrontFormat userByFrontFormat = new UserByFrontFormat();
            userByFrontFormat.setId(result.get(i).getUserId());
            userByFrontFormat.setOrganization(organizationService.getOrganizationNameByOid(result.get(i).getOrganization_id()));
            userByFrontFormat.setName(result.get(i).getUsername());
            userByFrontFormat.setRoles(roleService.getRolesName(result.get(i).getRole_ids()));
            userByFrontFormat.setLocking(result.get(i).getLocked());
            Log4jUtil.loggerInfo(userByFrontFormat.toString());
            frontDeskResult.add(userByFrontFormat);
        }
        return frontDeskResult;
    }

    @Override
    public void deleteUserById(Integer uid) throws Exception {
        userLoginMapper.deleteUserById(uid);
    }

    @Override
    public void editUserInfo(JSONObject userObject) throws Exception {
        Integer id = userObject.getInteger("id");
        String username = userObject.getString("name");
        List<Integer> org_strs = userObject.getJSONArray("selectedOption").toJavaList(Integer.class);
        Integer organization_id = org_strs.get(org_strs.size() - 1);
        List<String> roles_strs = userObject.getJSONArray("selectedRoles").toJavaList(String.class);
        String role_ids = "";
        for (String role_id: roles_strs) {
            role_ids += role_id + ",";
        }
        Boolean locked = !userObject.getBoolean("locking");

        UserLogin user = new UserLogin();
        user.setUserId(id);
        user.setUsername(username);
        user.setOrganization_id(organization_id);
        user.setRole_ids(role_ids);
        user.setLocked(locked);
        userLoginMapper.verifyUserInfo(user);
    }

    @Override
    public void addUser(JSONObject userObject) throws Exception {
        int id = getSysMaxUserID() + 1;
        String username = userObject.getString("name");
        String password = userObject.getString("password");
        List<Integer> tempList = userObject.getJSONArray("selectedOption").toJavaList(Integer.class);
        Integer organization_id = tempList.get(tempList.size() - 1);
        List<String> role_id_tempList = userObject.getJSONArray("selectedRoles").toJavaList(String.class);
        String roles_id = "";
        for (String role_id: role_id_tempList) {
            roles_id += role_id + ",";
        }
        Boolean locking = userObject.getBoolean("locking");
        UserLogin user = new UserLogin();
        user.setUserId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setOrganization_id(organization_id);
        user.setRole_ids(roles_id);
        user.setLocked(locking);
        userLoginMapper.createUser(user);
    }

    @Override
    public int getSysMaxUserID() throws Exception {
        int maxValue = userLoginMapper.findMax();
        return maxValue;
    }

    @Override
    public void resetUserPassword(int uid) throws Exception {
        userLoginMapper.resetUserPwd(uid);
    }
}
