package com.course.cases;

import com.course.model.GetUserInfo;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "获取用户信息接口测试")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlsession();
        GetUserInfo getUserInfo=sqlSession.selectOne("getuserinfo", 1);
        System.out.println("数据查询结果是"+getUserInfo.toString());
    }

}
