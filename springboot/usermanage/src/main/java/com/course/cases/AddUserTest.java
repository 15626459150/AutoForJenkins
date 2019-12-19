package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUser;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
    public void addUser() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlsession();
        System.out.println(TestConfig.addUserUrl);
        System.out.println("获取的sqlSession是"+sqlSession);
        AddUser adduser=sqlSession.selectOne("adduser", 1);
        System.out.println("数据查询结果是"+adduser.toString());
        String result=getResult(adduser);
        Assert.assertEquals(result, adduser.getExpected());
    }
    public String getResult(AddUser adduser) throws IOException {
        HttpPost post=new HttpPost(TestConfig.addUserUrl);
        System.out.println("添加用户接口地址是"+TestConfig.addUserUrl);
        JSONObject object=new JSONObject();
        object.put("username", adduser.getUsername());
        object.put("password", adduser.getPassword());
        object.put("age", adduser.getAge());
        object.put("sex", adduser.getSex());
        object.put("permission", adduser.getPermission());
        object.put("isdelete", adduser.getIsdelete());
        StringEntity entity=new StringEntity(object.toString(), "utf-8");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String res=EntityUtils.toString(response.getEntity(),"utf-8");
        return res;

    }
}
