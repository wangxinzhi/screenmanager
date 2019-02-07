package com.system.pojo;

/**
 * 组织部门实体和数据库organization表对应
 */
public class Organization {
    private Integer id; //编号
    private String name; //组织机构名称
    private Long parent_id; //父编号
    private String parent_ids; //父编号列表，如1/2/
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

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parentId) {
        this.parent_id = parentId;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parentIds) {
        this.parent_ids = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "{id="+id+" name="+name+" parent_id="+parent_id+" parent_ids="+parent_ids+" available="+available+"}";
    }
}
