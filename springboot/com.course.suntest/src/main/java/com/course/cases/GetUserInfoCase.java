package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfo;
import com.course.model.User;
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

public class GetUserInfoCase {
@Test
    public void GetUserInfoCase() throws IOException {
        SqlSession sqlSession= DataBaseUtil.getSqlSession();
        GetUserInfo getUserInfo=sqlSession.selectOne("getuserinfo", 1);
        User  user=sqlSession.selectOne(getUserInfo.getExpected(), getUserInfo.getUserid());
        String s=getResult(getUserInfo);
        Assert.assertEquals(s, user.toString());
    }

    private String getResult(GetUserInfo getUserInfo) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getUserInfoUrl);
        post.setHeader("content-type","application/json");
        JSONObject object=new JSONObject();
        object.put("id", getUserInfo.getUserid());
        StringEntity entity=new StringEntity(object.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity());
        return result;
    }

}
