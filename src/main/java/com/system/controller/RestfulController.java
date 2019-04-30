package com.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.system.Utils.Log4jUtil;
import com.system.Utils.UploadFileUtil;
import com.system.pojo.*;
import com.system.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class RestfulController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ScreenService screenService;

    @Autowired
    private SessionService sessionService;

    //********************  Login  ********************//

    @RequestMapping(value = "/login.do")
    public String login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "rememberMe", required = false) Boolean rememberMe){
        RestfulResult restfulResult = new RestfulResult();
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);// implements HostAuthenticationToken, RememberMeAuthenticationToken
        token.setRememberMe(rememberMe);
        try{
            //登陆
            subject.login(token);
            jsonObject.put("info","success");
            jsonObject.put("code", 20000);
            restfulResult.setCode(20000);
            restfulResult.setToken(subject.getSession().getId().toString());// 返回Token(数据是: SessionId)
            restfulResult.setData(subject.getPrincipal().toString());
            jsonObject.put("data", restfulResult);
            Log4jUtil.loggerInfo("用户: " + subject.getPrincipal().toString());
            Log4jUtil.loggerInfo("是否登陆: " + subject.isAuthenticated());
            Log4jUtil.loggerInfo("SessionId = " + subject.getSession().getId().toString());
            return jsonObject.toJSONString();
        }catch (AuthenticationException e){
            e.printStackTrace();
            jsonObject.put("error","LoginError");
            jsonObject.put("code",50008);
            restfulResult.setCode(50008);
            jsonObject.put("data", restfulResult);
            return jsonObject.toJSONString();
        }
    }

    /**
     * Logout 登出
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout.do")
    public String logout(@RequestParam(value = "token") String token)throws Exception{
        JSONObject jsonObject = new JSONObject();
        try {
            sessionService.forceLogout(token);
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

    @RequestMapping(value = "/test.do")
    public String test(){
        System.out.println("Restful Test");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data","This is a test data.");
        return jsonObject.toJSONString();
    }

    //********************  User  ********************//

    /***
     * Users 列表分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/users.do")
    public String getUsers(@RequestParam(value = "pagenum", defaultValue = "1") int pageNum, @RequestParam(value = "pagesize", defaultValue = "10") int pageSize){
        //PageHelper.startPage(pageNum, pageSize);
        //JSONObject jsonObject = new JSONObject();
        return "";
    }

    /**
     * 依据用户的 SESSION_ID 获取前端(重要)
     * @param token
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/info")
    public String getUserInfo(@RequestParam(value = "token") String token, HttpServletRequest request, HttpServletResponse response)throws Exception {
        Log4jUtil.loggerInfo(" ===============================================================================");
        Log4jUtil.loggerInfo(" ===============================================================================");
        Log4jUtil.loggerInfo(" ===============================================================================");
        String result = userService.getUserInfo(token, request, response);
        return result;
    }

    /**
     * Users 获取登陆用户管理权限下的用户列表 还要完善
     * @param username (eg: admin)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userlist.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "user:view")
    public String getUsersByName(@RequestParam(value = "username") String username)throws Exception {
        List<UserByFrontFormat> frontFromatUsers = userService.findUsersForFrontDesk(username);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(frontFromatUsers);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Add User 增加新用户
     * @param form :{ username: , password: , organization: , role: , locking: false }
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/adduser.do")
    @RequiresPermissions(value = "user:create")
    public String addUser(@RequestParam(value = "form", defaultValue = "") String form)throws Exception{
        Log4jUtil.loggerInfo("-----------" + form + "-----------");
        JSONObject userObject = JSON.parseObject(form);
        userService.addUser(userObject);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", "successAdd");
        return jsonObject.toJSONString();
    }

    /**
     * Delete User By Id 依据用户id删除用户信息
     * @param uid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteuser.do")
    @RequiresPermissions(value = "user:delete")
    public String deleteUser(@RequestParam(value = "uid") int uid)throws Exception{
        userService.deleteUserById(uid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", "successDelete");
        return jsonObject.toJSONString();
    }

    /**
     * Verify UserInfo 修改用户信息
     * @param editform :{ id: , name: , selectedOption: [], selectedRoles: [], locking: false }
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edituser.do")
    @RequiresPermissions(value = "user:update")
    public String editUser(@RequestParam(value = "editform") String editform)throws Exception{
        JSONObject userObject = JSON.parseObject(editform);
        userService.editUserInfo(userObject);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", "successEdit");
        return jsonObject.toJSONString();
    }

    /**
     * Reset UserPassword 重置用户密码为 123456
     * @param uid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resetpassword.do")
    public String resetUserPwd(@RequestParam(value = "uid") Integer uid)throws Exception{
        userService.resetUserPassword(uid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", "successResetPassword");
        return jsonObject.toJSONString();
    }

    //********************  Role  ********************//

    /**
     * Roles 前台获取JSON格式的roles
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roles.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "role:view")
    public String getSysRoles()throws Exception{
        List<RoleByFrontFormat> list = roleService.findRolesForFrontDesk();
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * AddRole 添加新的角色
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addrole.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "role:create")
    public String addRoles(@RequestParam(value = "form") String form)throws Exception{
        Log4jUtil.loggerInfo(form);
        JSONObject  roleObject = JSON.parseObject(form);
        roleService.addRole(roleObject);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     *
     * @param editform
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editrole.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "role:update")
    public String editRole(@RequestParam(value = "editform") String editform)throws Exception{
        JSONObject editObject = JSON.parseObject(editform);
        roleService.editRole(editObject);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * DeleteRole 删除角色
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleterole.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "role:delete")
    public String deleteRole(@RequestParam(value = "id") String id)throws Exception{
        roleService.delete(Integer.valueOf(id));
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    //********************  Organization  ********************//

    /**
     * OrgTree 前台获取JSON格式的系统部门树
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/orgtree.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "organization:view")
    public String getSystemOrgTree()throws Exception{
        List<OrganizationByFrontFormat> list = organizationService.getOrganizationTree();
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Add SystemOrg
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addorg.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "organization:create")
    public String addSystemOrg(@RequestParam(value = "form") String form)throws Exception{
        JSONObject orgObject = JSON.parseObject(form);
        organizationService.addSystemOrg(orgObject);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Delete SystemOrg
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteorg.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "organization:delete")
    public String deleteSystemOrg(@RequestParam(value = "id") String id)throws Exception{
        organizationService.delete(Integer.parseInt(id));
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    //********************  Resource  ********************//

    /**
     * ResTree 前台获取JSON格式的系统权限资源树
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/restree.do", produces = "application/json;charset=utf-8")
    public String getSystemResTree()throws Exception{
        List<ResourceByFrontFormat> list = resourceService.getResourceTree();
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    //********************  Program  ********************//

    /**
     * Programs 前台获取JSON格式的节目信息
     * @param username
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/programs.do", produces = "application/json;charset=utf-8")
    public String getPrograms(@RequestParam(value = "username") String username)throws Exception{
        List<ProgramByFrontFormat> list = programService.findProgramsForFrontDesk(username);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Add Program 添加节目
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addprogram.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "program:create")
    public String addPrograms(@RequestParam(value = "form") String form)throws Exception{
        JSONObject programObject = JSON.parseObject(form);
        programService.addProgram(programObject);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Edit Program Info 修改节目信息
     * @param editform
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editprogram.do", produces = "application/json;charset=utf-8")
    public String editPrograms(@RequestParam(value = "editform") String editform)throws Exception{
        JSONObject editObject = JSON.parseObject(editform);
        programService.editProgram(editObject);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Delete Program 删除节目
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteprogram.do", produces = "application/json;charset=utf-8")
    public String deletePrograms(@RequestParam(value = "id") int id)throws Exception{
        programService.deleteProgram(id);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Get screens 前端获取系统管理的全部屏幕的信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getscreens.do", produces = "application/json;charset=utf-8")
    public String getScreens()throws Exception{
        List<ScreenByFrontFormat> list = screenService.findScreensForFrontDesk(screenService.getSysAllScreens());
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Get VerifyPrograms 获取待审核列表
     * @param username
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/verifylist.do", produces = "application/json;charset=utf-8")
    public String getVerifyList(@RequestParam(value = "username") String username)throws Exception{
        List<ProgramByFrontFormat> list = programService.getVerifyProgramsForFrontDesk(username);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);
        restfulResult.setData(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    /**
     * Verify Program 审核节目信息
     * @param verifyform
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/verifyprogram.do", produces = "application/json;charset=utf-8")
    @RequiresPermissions(value = "program:ckeck")
    public String verifyProgram(@RequestParam(value = "verifyform") String verifyform)throws Exception{
        JSONObject verifyObject = JSON.parseObject(verifyform);
        programService.verifyProgram(verifyObject);
        RestfulResult restfulResult = new RestfulResult();
        restfulResult.setCode(20000);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("data", restfulResult);
        return jsonObject.toJSONString();
    }

    //********************  Upload  ********************//

    @RequestMapping(value = "/upload.do", produces = "application/json;charset=utf-8")
    public String upload(@RequestParam("file")MultipartFile file, HttpServletRequest request)throws Exception{
        String full_path_name = UploadFileUtil.upload(file, request);

        RestfulResult restfulResult = new RestfulResult();
        JSONObject jsonObject =  new JSONObject();

        if (full_path_name != "") {
            System.out.println("上传成功!!!");
            restfulResult.setCode(20000);
            restfulResult.setData(full_path_name);
            jsonObject.put("success", true);
        } else {
            System.out.println("上传失败!!!");
            restfulResult.setCode(50000);
            restfulResult.setData("上传失败");
            jsonObject.put("success", false);
        }
        jsonObject.put("data",restfulResult);
        return jsonObject.toJSONString();
    }


}
