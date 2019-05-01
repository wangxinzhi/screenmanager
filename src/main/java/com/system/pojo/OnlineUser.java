package com.system.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

// 在线用户 pojo
public class OnlineUser implements Serializable {

    private String id; // sessionId

    private String uid; // 用户ID

    private String username; // 用户名

    private String host; // 主机地址

    private String address; // 地理位置

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime; // 用户开始访问时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime; // 用户最后访问时间

    private Long timeout; // 超时时间

    private String status; // 状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : host;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
