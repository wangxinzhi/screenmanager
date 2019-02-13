package com.system.pojo;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 节目实体
 */
public class Program {
    private Integer ID;//节目ID
    private String PName;//节目名称
    private String PContent;//节目内容
    private String ScreensList;//节目发布的大屏List编号
    private Date PBeginTime;//节目开始时间
    private Date PEndTime;//节目结束时间
    private String PUrl;//节目的URL
    private Integer PSendPersonId;//节目发布人id
    private String PSendPerson;//节目的发布人用户名
    private Integer PType = 0;//节目类型（0--图片类型节目；1--视频类型节目：2--文字类型节目）
    private Integer PJudge = 0;//判断节目是否发布过（0--节目未审核；1--节目审核通过：2--审核未通过：3--成功发布）

    public Integer getID() {
        return ID;
    }

    public String getPName() {
        return PName;
    }

    public String getPContent() {
        return PContent;
    }

    public String getScreensList() {
        return ScreensList;
    }

    public Date getPBeginTime() {
        return PBeginTime;
    }

    public Date getPEndTime() {
        return PEndTime;
    }

    public String getPUrl() {
        return PUrl;
    }

    public Integer getPSendPersonId() {
        return PSendPersonId;
    }

    public String getPSendPerson() {
        return PSendPerson;
    }

    public Integer getPType() {
        return PType;
    }

    public Integer getPJudge() {
        return PJudge;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public void setPContent(String PContent) {
        this.PContent = PContent;
    }

    public void setScreensList(String ScreensList) {
        this.ScreensList = ScreensList;
    }

    public void setPBeginTime(Date PBeginTime) {
        this.PBeginTime = PBeginTime;
    }

    public void setPEndTime(Date PEndTime) {
        this.PEndTime = PEndTime;
    }

    public void setPUrl(String PUrl) {
        this.PUrl = PUrl;
    }

    public void setPSendPersonId(Integer PSendPersonId) {
        this.PSendPersonId = PSendPersonId;
    }

    public void setPSendPerson(String PSendPerson) {
        this.PSendPerson = PSendPerson;
    }

    public void setPType(Integer PType) {
        this.PType = PType;
    }

    public void setPJudge(Integer PJudge) {
        this.PJudge = PJudge;
    }

    @Override
    public String toString() {
        return "ID=" + ID + "  PName=" + PName + "  PContent=" + PContent + "  ScreensList=" + ScreensList + "  PBeginTime=" + PBeginTime + "  PEndTime=" + PEndTime + "  PUrl=" + PUrl + "  PType=" + PType;
    }

    public String formatBeginTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(PBeginTime);
    }

    public String formatEndTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(PEndTime);
    }
}
