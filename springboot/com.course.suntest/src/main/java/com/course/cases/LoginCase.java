package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.Login;
import com.course.utils.Configfile;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginCase {
    @BeforeTest
    public void BeforeTest() throws IOException {
        TestConfig.loginUrl= Configfile.getUrl("login.uri");
        TestConfig.addUserUrl=Configfile.getUrl("addUser.uri");
        TestConfig.getUserInfoUrl=Configfile.getUrl("getUserInfo.uri");
        TestConfig.getUserListUrl=Configfile.getUrl("getUserList.uri");
        TestConfig.updateUserInfoUrl=Configfile.getUrl("updateUserInfo.uri");
        TestConfig.defaultHttpClient=new DefaultHttpClient();

    }
    @Test
    public void LoginSuccess() throws IOException {
        SqlSession sqlSession= DataBaseUtil.getSqlSession();
        Login login=sqlSession.selectOne("login", 1);
        String s=getLoginResult(login);
        String expect=login.getExpected();
        Assert.assertEquals(s,expect);
    }
    @Test
    public void LoginFail() throws IOException {
        SqlSession sqlSession= DataBaseUtil.getSqlSession();
        Login login=sqlSession.selectOne("login", 2);
        String s=getLoginResult(login);
        String expect=login.getExpected();
        Assert.assertEquals(s,expect);
    }


    private String getLoginResult(Login login) throws IOException {
        HttpPost post=new HttpPost(TestConfig.loginUrl);
        post.setHeader("content-type","application/json");
        JSONObject object=new JSONObject();
        object.put("username", login.getUsername());
        object.put("password", login.getPassword());
        StringEntity entity=new StringEntity(object.toString(), "UTF-8");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity());
        return result;
    }
}
