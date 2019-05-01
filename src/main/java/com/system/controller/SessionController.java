package com.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.system.pojo.OnlineUser;
import com.system.pojo.RestfulResult;
import com.system.service.SessionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    /**
     * SESSIONS 获取在线用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sessions.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "monitor:online")
    public String getSessions()throws Exception {
        List<OnlineUser> result = sessionService.list();
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(result);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * FORCE_LOGOUT 强制登出
     * @param sessionId
     * @return
     * @throws Exception
     */
    @RequestMapping("/forcelogout.do")
    @RequiresPermissions(value = "monitor:offline")
    public String forceLogout(@RequestParam(value = "sessionId") String sessionId)throws Exception{
        JSONObject jsonObject = new JSONObject();
        try {
            sessionService.forceLogout(sessionId);
            jsonObject.put("code", 20000);
            jsonObject.put("data", "force logout success.");
            return jsonObject.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code", 50000);
            jsonObject.put("data", "force logout failed.");
            return jsonObject.toJSONString();
        }
    }


}
