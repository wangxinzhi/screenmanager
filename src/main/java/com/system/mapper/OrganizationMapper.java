package com.system.mapper;

import com.system.pojo.Organization;

import java.util.List;

public interface OrganizationMapper {

    Organization findOne(Integer id);

    List<Organization> findAll();

    void create(Organization organization);

    void delete(Integer id);

    void update(Organization organization);
}
