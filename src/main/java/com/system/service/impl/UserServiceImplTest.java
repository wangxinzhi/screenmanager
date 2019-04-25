package com.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.system.Utils.Log4jUtil;
import com.system.mapper.RoleMapper;
import com.system.mapper.UserLoginMapper;
import com.system.pojo.RestfulResult;
import com.system.pojo.UserLogin;
import com.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring/applicationContext-*.xml")
public class UserServiceImplTest {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserService userService;

    @Test
    public void verifyUserInfo() throws Exception {
        String TestData = "{\"id\":2,\"name\":\"teacher\",\"selectedOption\":[1,2],\"selectedRoles\":[\"admin\"],\"locking\":true}";
        JSONObject userObject = JSON.parseObject(TestData);
        Integer id = userObject.getInteger("id");
        String username = userObject.getString("name");
        List<Integer> org_strs = userObject.getJSONArray("selectedOption").toJavaList(Integer.class);
        Integer organization_id = org_strs.get(org_strs.size()-1);
        List<String> roles_strs = userObject.getJSONArray("selectedRoles").toJavaList(String.class);
        String role_ids = "";
        for (int i = 0; i < roles_strs.size(); ++i){
            role_ids += roleMapper.getRoleId(roles_strs.get(i));
            role_ids += ",";
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

    @Test
    public void resetUserPasswordTest()throws Exception{
        userService.resetUserPassword(4);
    }

    @Test
    public void getUserInfoTest()throws Exception{
        String name = "admin";
        String sessionId = "sgagjddsaffacraacagcddfaqrtt";
        UserLogin userLogin = userLoginMapper.getUser(name);
        String[] roles = userLogin.getRole_ids().split(",");
        String resources = "";
        for (String role_id:roles) {
            resources += roleMapper.selectByPrimaryKey(Integer.valueOf(role_id)).getResource_ids() + ",";
        }
        String[] resources_id = resources.split(",");

        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setToken(sessionId);
        restfulResult.setData(resources_id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("info","success");
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        Log4jUtil.loggerInfo(jsonObject.toJSONString());
    }


}