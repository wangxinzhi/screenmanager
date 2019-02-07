package com.system.service.impl;

import com.system.mapper.OrganizationMapper;
import com.system.pojo.Organization;
import com.system.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public Organization findOne(Integer id) throws Exception {
        return organizationMapper.findOne(id);
    }

    @Override
    public List<Organization> findAll() throws Exception {
        return organizationMapper.findAll();
    }

    @Override
    public void create(Organization organization) throws Exception {
        organizationMapper.create(organization);
    }

    @Override
    public void delete(Integer id) throws Exception {
        organizationMapper.delete(id);
    }

    @Override
    public void update(Organization organization) throws Exception {
        organizationMapper.update(organization);
    }
}
