package com.course.cases;

import com.course.model.GetUserInfo;
import com.course.model.UpdateUserInfo;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息接口测试")
    public void updateUserInfo() throws IOException {
        SqlSession sqlSession= DatabaseUtil.getSqlsession();
        UpdateUserInfo updateUserInfo=sqlSession.selectOne("updateuserinfo", 1);
        System.out.println("数据查询结果是"+updateUserInfo.toString());
}

}
