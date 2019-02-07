package com.system.service.impl;

import com.system.Utils.Log4jUtil;
import com.system.mapper.ScreenMapper;
import com.system.pojo.Screen;
import com.system.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenMapper screenMapper;

    @Override
    public List<Screen> getScreensByOrganizationId(Integer organizationId) throws Exception {
        String screens=screenMapper.getScreens(organizationId);
        List<Screen> result=new ArrayList<>();
        for (String id:
             screens.split(",")) {
            result.add(screenMapper.getScreen(Integer.parseInt(id)));
        }
        return result;
    }

    @Override
    public String getLocationsByStr(String str) throws Exception {
        String result="";
        for (String id:
             str.split(",")) {
            result+=result+screenMapper.getScreen(Integer.parseInt(id)).getLocation()+",";
        }
        return result;
    }

    @Override
    public List<Screen> getScreensByStr(String str) throws Exception {
        List<Screen> result=new ArrayList<>();
        for (String id:
             str.split(",")) {
            result.add(screenMapper.getScreen(Integer.parseInt(id)));
        }
        return result;
    }
}
