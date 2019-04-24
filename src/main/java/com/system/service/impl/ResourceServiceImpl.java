package com.system.service.impl;

import com.system.mapper.ResourceMapper;
import com.system.pojo.Organization;
import com.system.pojo.OrganizationByFrontFormat;
import com.system.pojo.Resource;
import com.system.pojo.ResourceByFrontFormat;
import com.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Override
    public List<ResourceByFrontFormat> getResourceTree() throws Exception {
        List<Resource> resourceList = resourceMapper.findAll();// 从数据库获取系统所有的 pojo Organization.class 对象

        List<ResourceByFrontFormat> resourceByFrontFormats = new ArrayList<>();// 存储系统所有的 Organization 信息
        for (Resource node: resourceList) {
            ResourceByFrontFormat frontFormat = new ResourceByFrontFormat();
            frontFormat.setId(node.getId());
            frontFormat.setLabel(node.getName());
            frontFormat.setPid(node.getParent_id().intValue());
            resourceByFrontFormats.add(frontFormat);
        }

        List<ResourceByFrontFormat> resultTree = new ArrayList<>();// 返回前端的结果集
        for (ResourceByFrontFormat node: resourceByFrontFormats) {
            if (node.getPid() == 0){// 找到根
                resultTree.add(node);
            }
            for (ResourceByFrontFormat o: resourceByFrontFormats) {// 找到子叶
                if (o.getPid() == node.getId()){
                    if (node.getChildren() == null){
                        node.setChildren(new ArrayList<ResourceByFrontFormat>());
                    }
                    node.getChildren().add(o);
                }
            }
        }
        return resultTree;
    }
}
