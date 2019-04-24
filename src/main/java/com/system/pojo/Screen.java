package com.system.pojo;

public class Screen {

    private Integer id;//id
    private String location;//位置
    private String ip;//ip
    private String remarks;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ id=");
        stringBuilder.append(id);
        stringBuilder.append(" location = ");
        stringBuilder.append(location);
        stringBuilder.append(" ip = ");
        stringBuilder.append(ip);
        stringBuilder.append(" remarks = ");
        stringBuilder.append(remarks);
        return stringBuilder.toString();
    }
}
