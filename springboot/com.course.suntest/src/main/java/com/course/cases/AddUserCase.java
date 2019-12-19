package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUser;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserCase {
    @Test
    public void addUser() throws IOException {
        SqlSession sqlSession= DataBaseUtil.getSqlSession();
        AddUser addUser=sqlSession.selectOne("adduser", 1);
        String s=getResult(addUser);
        Assert.assertEquals(s, addUser.getExpected());
    }

    private String getResult(AddUser addUser) throws IOException {
        HttpPost post=new HttpPost(TestConfig.addUserUrl);
        post.setHeader("content-type","application/json");
        JSONObject object=new JSONObject();
        object.put("id", addUser.getId());
        object.put("username", addUser.getUsername());
        object.put("password", addUser.getPassword());
        object.put("age", addUser.getAge());
        object.put("permission", addUser.getPermission());
        object.put("sex", addUser.getSex());
        object.put("isdelete", addUser.getIsdelete());
        StringEntity entity=new StringEntity(object.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result=EntityUtils.toString(response.getEntity());
        return result;








    }
}
