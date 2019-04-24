package com.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.system.Utils.Log4jUtil;
import com.system.mapper.OrganizationMapper;
import com.system.pojo.Organization;
import com.system.pojo.OrganizationByFrontFormat;
import com.system.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Override
    public String getOrganizationNameByOid(Integer organizationId) throws Exception {
        String organizationName = findOne(organizationId).getName();
        return organizationName;
    }

    /**
     * 获取系统 部门树 (用于前端API)
     * @return
     * @throws Exception
     */
    @Override
    public List<OrganizationByFrontFormat> getOrganizationTree() throws Exception {
        List<Organization> organizationList = organizationMapper.findAll();// 从数据库获取系统所有的 pojo Organization.class 对象
        Collections.sort(organizationList, new Comparator<Organization>() {
            @Override
            public int compare(Organization o1, Organization o2) {// 比较函数 用于排序 0代表相等
                int i = o1.getParent_id().intValue() - o2.getParent_id().intValue();
                if (i == 0){
                    return o1.getId() - o2.getId();
                }
                return i;
            }
        });
        List<OrganizationByFrontFormat> organizationByFrontFormatList = new ArrayList<>();// 存储系统所有的 Organization 信息
        for (Organization node: organizationList) {
            OrganizationByFrontFormat frontFormat = new OrganizationByFrontFormat();
            frontFormat.setValue(node.getId());
            frontFormat.setLabel(node.getName());
            frontFormat.setPid(node.getParent_id().intValue());
            organizationByFrontFormatList.add(frontFormat);
        }
        
        List<OrganizationByFrontFormat> resultTree = new ArrayList<>();// 返回前端的结果集
        for (OrganizationByFrontFormat node: organizationByFrontFormatList) {
            if (node.getPid() == 0){// 找到根
                resultTree.add(node);
            }
            for (OrganizationByFrontFormat o: organizationByFrontFormatList) {// 找到子叶
                if (o.getPid() == node.getValue()){
                    if (node.getChildren() == null){
                        node.setChildren(new ArrayList<OrganizationByFrontFormat>());
                    }
                    node.getChildren().add(o);
                }
            }
        }
        return resultTree;
    }

    @Override
    public void addSystemOrg(JSONObject orgObject) throws Exception {
        Integer id = getMaxSystemOrgID() + 1;
        String orgName = orgObject.getString("depart");
        Long pid = orgObject.getLong("pid");
        String purl = getParentIDs(pid.intValue());
        Boolean state = true;
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(orgName);
        organization.setParent_id(pid);
        organization.setParent_ids(purl);
        organization.setAvailable(state);
        organizationMapper.create(organization);
    }

    @Override
    public Integer getMaxSystemOrgID() throws Exception {
        Integer maxID = organizationMapper.findMax();
        return maxID;
    }

    @Override
    public String getParentIDs(Integer pid) throws Exception {
        String parent_ids = organizationMapper.findOne(pid).getParent_ids();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parent_ids);
        stringBuilder.append(pid);
        stringBuilder.append("/");
        return stringBuilder.toString();
    }
}
