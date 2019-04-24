package com.system.pojo;

/***
 * Role前端格式
 */
public class RoleByFrontFormat {

    private Integer id;// 角色的 id
    private String value;// 角色的 role
    private String label;// 角色的 description
    private String resourceid;// 角色拥有的资源id
    private String resource;// 角色拥有的资源; eg: 用户查看
    private Boolean state;// 状态 true = 可用 / false = 禁用

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ RoleByFrontFormat.class ");
        stringBuilder.append("id = ");
        stringBuilder.append(id);
        stringBuilder.append(" role = ");
        stringBuilder.append(value);
        stringBuilder.append(" label = ");
        stringBuilder.append(label);
        stringBuilder.append(" resourceID = ");
        stringBuilder.append(resourceid);
        stringBuilder.append(" resource = ");
        stringBuilder.append(resource);
        stringBuilder.append(" state = ");
        stringBuilder.append(state);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
