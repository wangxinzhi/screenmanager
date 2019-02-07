package com.system.service.impl;

import com.system.mapper.ShowPagesMapper;
import com.system.pojo.Category;
import com.system.pojo.Page;
import com.system.service.ShowPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowPagesServiceImpl implements ShowPagesService{

    @Autowired()
    ShowPagesMapper showPagesMapper;

    @Override
    public List<Category> getAll() throws Exception {
        return showPagesMapper.getAll();
    }

    @Override
    public Integer Count() throws Exception {
        return showPagesMapper.Count();
    }

    @Override
    public List<Category> getAll(Page page)throws Exception{
        return showPagesMapper.getAll(page);
    }
}
