package com.system.service.impl;

import com.system.mapper.ResourceMapper;
import com.system.pojo.Resource;
import com.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource findOne(Integer id) throws Exception {
        return resourceMapper.findOne(id);
    }

    @Override
    public List<Resource> findAll() throws Exception {
        return resourceMapper.findAll();
    }

    @Override
    public void create(Resource resource) throws Exception {
        resourceMapper.create(resource);
    }

    @Override
    public void delete(Integer id) throws Exception {
        resourceMapper.delete(id);
    }

    @Override
    public void update(Resource resource) throws Exception {
        resourceMapper.update(resource);
    }
}
