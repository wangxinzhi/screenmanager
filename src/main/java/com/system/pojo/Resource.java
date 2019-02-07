package com.system.pojo;

/**
 * 资源实体和数据库resource表对应
 */
public class Resource {
    private Integer id; //编号
    private String name; //资源名称
    private String type; //资源类型
    private String url; //资源路径
    private Integer parent_id; //父编号
    private String parent_ids; //父编号列表
    private String permission;//权限(资源 : 操作 : 实体)
    private Boolean available = Boolean.FALSE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parentId) {
        this.parent_id = parentId;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parentIds) {
        this.parent_ids = parentIds;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "{id="+id+" name="+name+" type="+type+" url="+url+" parent_id="+parent_id+" parent_ids="+parent_ids+" permission="+permission+" available="+available+"}";
    }
}
