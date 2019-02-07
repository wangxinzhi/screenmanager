package com.system.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * role实体的工具类
 */
public class RoleUtil {

    public static List<Integer> getRoleIdsByIntegerList(String roleIds){
        List<Integer> result=new ArrayList<>();
        for (String i:roleIds.split(",")) {
            if (i == null){
                continue;
            }
            else result.add(Integer.parseInt(i));
        }
        return result;
    }

    public static List<Integer> getResourceIdsByIntegerList(String resourcesIds){
        List<Integer> result=new ArrayList<Integer>();
        for(String i : resourcesIds.split(",")){
            if (i==null){
                continue;
            }
            else result.add(Integer.parseInt(i));

        }
        return result;
    }

}
