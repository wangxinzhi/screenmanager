package com.system.pojo;

import java.util.List;

public class ResourceByFrontFormat {

    private Integer id;// 权限资源id
    private String label;// 权限资源名称
    private Integer pid;// 父id
    private List<ResourceByFrontFormat> children;// 子权限资源

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ResourceByFrontFormat> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceByFrontFormat> children) {
        this.children = children;
    }
}
