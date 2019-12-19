package com.course.utils;
import org.apache.http.impl.client.DefaultHttpClient;
import sun.util.resources.cldr.lag.CurrencyNames_lag;

import java.util.Locale;
import java.util.ResourceBundle;

public class Configfile {
    static String url;
    public static String getUrl(String key){
        url=new String();
        ResourceBundle bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        String testurl=bundle.getString("test.url");
        if(key.equals("login.uri")){
            url=bundle.getString("login.uri");
        }else if(key.equals("updateUserInfo.uri")){
            url=bundle.getString("updateUserInfo.uri");
        }else if(key.equals("getUserList.uri")){
            url=bundle.getString("getUserList.uri");
        }else if(key.equals("getUserInfo.uri")){
            url=bundle.getString("getUserInfo.uri");
        }else if(key.equals("addUser.uri")){
            url=bundle.getString("addUser.uri");
        }
        return testurl+url;
    }

}
