package com.system.pojo;

import java.util.List;

public class OrganizationByFrontFormat {

    private Integer value;// 部门id
    private String label;// 部门名称
    private Integer pid;// 父id
    private List<OrganizationByFrontFormat> children;// 子部门

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<OrganizationByFrontFormat> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationByFrontFormat> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" [ OrganizationByFrontFormat.class ] ");
        stringBuilder.append("id = ");
        stringBuilder.append(value);
        stringBuilder.append(" label = ");
        stringBuilder.append(label);
        stringBuilder.append(" pid = ");
        stringBuilder.append(pid);
        return stringBuilder.toString();
    }
}
