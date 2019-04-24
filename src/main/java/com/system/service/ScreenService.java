package com.system.service;

import com.system.pojo.Screen;
import com.system.pojo.ScreenByFrontFormat;

import java.util.List;

public interface ScreenService {

    List<Screen> getScreensByOrganizationId(Integer id)throws Exception;

    String getLocationsByStr(String str)throws Exception;

    List<Screen> getScreensByStr(String str)throws Exception;

    List<Screen> getSysAllScreens()throws Exception;

    List<ScreenByFrontFormat> findScreensForFrontDesk(List<Screen> list)throws Exception;

}
