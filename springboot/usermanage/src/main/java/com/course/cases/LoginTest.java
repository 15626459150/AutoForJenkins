package com.course.cases;


import com.course.config.TestConfig;
import com.course.model.IntefaceName;
import com.course.model.Login;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "login",description = "这是测试前的准备工作")
    public void beforeTest(){
        TestConfig.loginUrl =ConfigFile.getUrl(IntefaceName.LOGIN);
        TestConfig.addUserUrl=ConfigFile.getUrl(IntefaceName.ADDUSER);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(IntefaceName.GETUSERINFO);
        TestConfig.getUserListUrl=ConfigFile.getUrl(IntefaceName.GETUSERLIST);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(IntefaceName.UPDATEUSERINFO);
        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description ="这是测试登陆正确接口")
    public void loginTrue() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlsession();
        System.out.println(TestConfig.loginUrl);
        System.out.println("获取的sqlSession是"+sqlSession);
        Login login=sqlSession.selectOne("login", 1);
        System.out.println("数据查询结果是"+login.toString());
        String result=getResult(login);
        Assert.assertEquals(result, login.getExpected());

    }



    @Test(groups = "login",description ="这是测试登陆错误接口")
    public void loginFail() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlsession();
        System.out.println(TestConfig.loginUrl);
        System.out.println("获取的sqlSession是"+sqlSession);
        Login login=sqlSession.selectOne("login", 2);
        System.out.println("数据查询结果是"+login.toString());

    }


    private String getResult(Login login) throws IOException {
        HttpPost post=new HttpPost(TestConfig.loginUrl);
        JSONObject param=new JSONObject();
        param.put("username", login.getUsername());
        param.put("password", login.getPassword());
        StringEntity entity=new StringEntity(param.toString(), "utf-8");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }
}
