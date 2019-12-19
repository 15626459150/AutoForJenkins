package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserList;
import com.course.utils.DataBaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class GetUserListCase {
    @Test
    public void getUserListCase() throws IOException {
        SqlSession sqlSession= DataBaseUtil.getSqlSession();
        GetUserList getUserList = sqlSession.selectOne("getuserlist", 1);
        JSONArray result=getResult(getUserList);
        List<Object> users=sqlSession.selectList(getUserList.getExpected(),getUserList);

        JSONArray array=new JSONArray(users);
        Assert.assertEquals(result,result);
    }

    private JSONArray getResult(GetUserList getUserList) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getUserListUrl);
        post.setHeader("content-type","application/json");
        JSONObject object=new JSONObject();
        if(getUserList.getAge()!=null){
            object.put("age", getUserList.getAge());
        }if(getUserList.getSex()!=null){
            object.put("sex", getUserList.getSex());
        }if(getUserList.getUsername()!=null){
            object.put("username", getUserList.getUsername());
        }
        StringEntity entity=new StringEntity(object.toString(), "utf-8");
        post.setEntity(entity);
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
        JSONArray array=new JSONArray(result);
        return array;
    }

}

