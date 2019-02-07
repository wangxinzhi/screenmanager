package com.system.service;

import com.system.pojo.Category;
import com.system.pojo.Page;

import java.util.List;

public interface ShowPagesService {

    List<Category> getAll()throws Exception;

    Integer Count()throws Exception;

    List<Category> getAll(Page page)throws Exception;
}
