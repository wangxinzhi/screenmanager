package com.system.service;

import com.system.pojo.OnlineUser;
import org.apache.shiro.session.Session;

import java.util.List;

public interface SessionService {

    List<OnlineUser> list()throws Exception;

    boolean forceLogout(String sessionId)throws Exception;

}
