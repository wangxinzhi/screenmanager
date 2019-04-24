package com.system.Utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {

    public String md5Hash(String param){
        // 1.传入密码
        String parameter = param;
        // 2.盐
        String salt = "paradox";
        // 3.得到的HASH值
        String md5Str = new Md5Hash(parameter, salt).toString();

        return md5Str;
    }
}
