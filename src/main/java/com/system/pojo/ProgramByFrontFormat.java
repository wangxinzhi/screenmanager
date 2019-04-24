package com.system.pojo;

import java.util.Date;

public class ProgramByFrontFormat {

    private Integer id;// 节目ID
    private String pname;// 节目名称
    private String pcontent;// 节目内容
    private String screenlist;// 节目发布的大屏List编号
    private Date starttime;// 节目开始时间
    private String starttime_localstr;// 节目开始时间的 localString
    private Date endtime;// 节目结束时间
    private String endtime_localstr;// 节目结束时间 localString
    private String purl;// 节目资源存储地址
    private Integer publisherId;// 发布人id
    private String publisher;// 发布人
    private Integer ptype = 0;// 节目类型（0--图片类型节目；1--视频类型节目：2--文字类型节目）
    private String ptypeStr = "";
    private Integer state = 0;// 节目状态（0--节目未审核；1--节目审核通过：2--审核未通过：3--成功发布）
    private String stateStr = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getScreenlist() {
        return screenlist;
    }

    public void setScreenlist(String screenlist) {
        this.screenlist = screenlist;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("screens = ");
        stringBuilder.append(screenlist);
        stringBuilder.append(" startTime = ");
        stringBuilder.append(starttime);
        stringBuilder.append(" endTime = ");
        stringBuilder.append(endtime);
        return stringBuilder.toString();
    }
}
