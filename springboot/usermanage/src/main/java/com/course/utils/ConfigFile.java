package com.course.utils;

import com.course.model.IntefaceName;

import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application");
    public static String getUrl(IntefaceName intefaceName){
        String address=bundle.getString("test.url");
        String uri="";
        if (intefaceName.equals(IntefaceName.LOGIN)){
            uri=bundle.getString("login.uri");
        }if (intefaceName.equals(IntefaceName.ADDUSER)){
            uri=bundle.getString("adduser.uri");
        }if (intefaceName.equals(IntefaceName.GETUSERINFO)){
            uri=bundle.getString("getuserinfo.uri");
        }if (intefaceName.equals(IntefaceName.GETUSERLIST)){
            uri=bundle.getString("getuserlist.uri");
        }if (intefaceName.equals(IntefaceName.UPDATEUSERINFO)){
            uri=bundle.getString("updateuser.uri");
        }
     return address+uri;
    }
}
