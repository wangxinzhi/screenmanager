package com.system.mapper;

import com.system.pojo.Resource;

import java.util.List;

public interface ResourceMapper {

    String findOnePermission(Integer id);

    Resource findOne(Integer id);

    List<Resource> findAll();

    void create(Resource resource);

    void delete(Integer id);

    void update(Resource resource);

}
