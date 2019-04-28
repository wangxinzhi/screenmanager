package com.system.service.impl;

import com.system.pojo.OnlineUser;
import com.system.pojo.UserLogin;
import com.system.service.SessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public List<OnlineUser> list() {
        List<OnlineUser> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        System.out.println("SESSIONS SIZE = " + sessions.size());
        for (Session session:sessions) {
            if (session != null) {
                OnlineUser onlineUser = new OnlineUser();
                SimplePrincipalCollection principalCollection;
                String username;
                if (session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
                    continue;
                } else {
                    principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                    username = (String) principalCollection.getPrimaryPrincipal();
                    onlineUser.setUsername(username);
                }
                onlineUser.setId(session.getId().toString());
                onlineUser.setHost(session.getHost());
                // onlineUser.setAddress(AddressUtil.getAddress(session.getHost()));
                onlineUser.setStartTime(session.getStartTimestamp());
                onlineUser.setEndTime(session.getLastAccessTime());
                long timeout = session.getTimeout();
                onlineUser.setTimeout(timeout);
                if (timeout == 0l) {
                    onlineUser.setStatus("离线");
                } else {
                    onlineUser.setStatus("在线");
                }
                list.add(onlineUser);
            }
        }
        System.out.println("SESSION LIST");
        System.out.println("RESULT SIZE = " + list.size());
        return list;
    }

    @Override
    public boolean forceLogout(String sessionId) {
        return false;
    }
}
