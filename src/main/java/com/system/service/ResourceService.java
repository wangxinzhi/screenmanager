package com.system.service;

import com.system.pojo.Resource;
import com.system.pojo.ResourceByFrontFormat;

import java.util.List;

public interface ResourceService {

    Resource findOne(Integer id)throws Exception;

    List<Resource> findAll()throws Exception;

    void create(Resource resource)throws Exception;

    void delete(Integer id)throws Exception;

    void update(Resource resource)throws Exception;

    // 获取系统 权限资源树 (用于前端API)
    List<ResourceByFrontFormat> getResourceTree()throws Exception;

}
