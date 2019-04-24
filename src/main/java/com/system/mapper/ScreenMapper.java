package com.system.mapper;

import com.system.pojo.Screen;

import java.util.List;

public interface ScreenMapper {
    //获取屏幕集
    String getScreens(Integer id);
    //获取一个屏幕的完整信息
    Screen getScreen(Integer id);
    //获取全部屏幕信息
    List<Screen> getAll();
    //更新表 screens
    void updateOnscreens(Screen screen);
    //更新表 screensgrouping
    void updateOnscreensgrouping(Integer id,String screens);
    //删除表 screens 信息
    void deleteOnscreens(Integer id);
    //删除表 screensgrouping 信息
    void deleteOnscreensgrouping(Integer id);
    //插入屏幕信息
    void insertOnscreens(Screen screen);
    //插入屏幕所有信息
    void insertOnscreensgrouping(Integer id,String screens);
}
