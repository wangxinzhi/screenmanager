package com.system.pojo;

//节目审核类
public class ProgramByCheck {

    private Integer pid;
    private Integer pjudge;
    private String feedback = null;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPjudge() {
        return pjudge;
    }

    public void setPjudge(Integer pjudge) {
        this.pjudge = pjudge;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
