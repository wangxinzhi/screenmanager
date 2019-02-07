package com.system.service;

import com.system.pojo.Resource;

import java.util.List;

public interface ResourceService {

    Resource findOne(Integer id)throws Exception;

    List<Resource> findAll()throws Exception;

    void create(Resource resource)throws Exception;

    void delete(Integer id)throws Exception;

    void update(Resource resource)throws Exception;

}
