package com.system.realm;

import com.system.pojo.UserLogin;
import com.system.service.UserLoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class LoginRealm extends AuthorizingRealm {

    @Resource(name = "userLoginServiceImpl")
    private UserLoginService userLoginService;

    /**
     * 授权
     * 获取身份信息，从数据库获取用户的角色和权限信息，调用权限验证时，调用此方法
     * @param principalCollection
     * @return authorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=(String)getAvailablePrincipal(principalCollection);

        //通过用户名从数据库获取角色/权限信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            authorizationInfo.setRoles(userLoginService.findRoles(username));
            authorizationInfo.setStringPermissions(userLoginService.findPermissions(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /**
     * 认证
     * 进行身份验证调用login()时调用
     * @param authenticationToken
     * @return authorizationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.从主体传过来的认证信息中，获得用户名
        String username = (String) authenticationToken.getPrincipal();
        //密码
        //String password = new String((char [])authenticationToken.getCredentials());
        UserLogin userLogin = null;
        try{
            userLogin = userLoginService.findByName(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(userLogin == null){
            //没有用户名
            throw new UnknownAccountException();
        }
        //身份验证通过返回一个身份信息
        AuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(userLogin.getUsername(),userLogin.getPassword(),getName());
        return authorizationInfo;
    }

    @Override
    public String getName(){
        return "LoginRealm";
    }
}


