package com.system.service;

import com.system.pojo.Organization;

import java.util.List;

public interface OrganizationService {

    Organization findOne(Integer id)throws Exception;

    List<Organization> findAll()throws Exception;

    void create(Organization organization)throws Exception;

    void delete(Integer id)throws Exception;

    void update(Organization organization)throws Exception;

}
