package com.system.service;

import com.alibaba.fastjson.JSONObject;
import com.system.pojo.Organization;
import com.system.pojo.OrganizationByFrontFormat;

import java.util.List;

public interface OrganizationService {

    Organization findOne(Integer id)throws Exception;

    List<Organization> findAll()throws Exception;

    void create(Organization organization)throws Exception;

    void delete(Integer id)throws Exception;

    void update(Organization organization)throws Exception;

    String getOrganizationNameByOid(Integer organizationId)throws Exception;

    // 获取系统 部门树 (用于前端API)
    List<OrganizationByFrontFormat> getOrganizationTree()throws Exception;

    // 添加部门
    void addSystemOrg(JSONObject orgObject)throws Exception;

    // 获得 organization 数据表中部门最大的id
    Integer getMaxSystemOrgID()throws Exception;

    // 获得部门树完整的路径 parent_ids (eg: 0/1/)
    String getParentIDs(Integer pid)throws Exception;
}
