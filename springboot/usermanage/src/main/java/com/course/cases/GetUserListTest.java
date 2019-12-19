package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserList;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
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

public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表接口测试")
    public void getUserList() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlsession();
        System.out.println(TestConfig.getUserListUrl);
        System.out.println("获取的sqlSession是"+sqlSession);
        GetUserList getUserList=sqlSession.selectOne("getuserlist", 1);
        System.out.println("数据查询结果是"+getUserList.toString());
        String result=getResult(getUserList);
        List<User> users=sqlSession.selectList(getUserList.getExpected(), getUserList);
        System.out.println(users);
        for(User user:users){
            System.out.println("获取到的用户名是"+user.getUsername());
        }
        String userString=users.toString();
      //  JSONArray array=new JSONArray(userString);
//        Assert.assertEquals(array.length(), result.length());
//        for (int i=0;i<array.length();i++){
//           JSONObject act= (JSONObject) array.get(i);
//           JSONObject exp=(JSONObject)result.get(i);
//           String a=act.toString();
//           String b=exp.toString();
//           Assert.assertEquals(act.toString(), exp.toString());

 //       }
        Assert.assertEquals(result,userString);

    }

    private String getResult(GetUserList getUserList) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getUserListUrl);
        JSONObject object=new JSONObject();
        object.put("username", getUserList.getUsername());
        object.put("age", getUserList.getAge());
        object.put("sex", getUserList.getSex());
        StringEntity entity=new StringEntity(object.toString(), "utf-8");
        post.setEntity(entity);
        post.setHeader("Content-Type","application/json");
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        String res=EntityUtils.toString(response.getEntity());
       // JSONArray array=new JSONArray(res);
        return res;
    }
}
