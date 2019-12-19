package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value="/",description="这是全部的post请求！")
public class MyPostMethod {
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登陆接口获取cookies",httpMethod = "post")
    public String getWithCookies(HttpServletResponse response,
                               @RequestParam(value = "用户名",required = true) String username,
                               @RequestParam(value="密码",required = true) String password){
        if(username.equals("zhangsan")&&password.equals("123456")){
            Cookie cookie =new Cookie("lily", "singer");
            response.addCookie(cookie);
            return "恭喜你登陆成功！";
        }
      return"用户名或密码错误";

    }
    @RequestMapping(value="/getUserList",method= RequestMethod.POST)
    @ApiOperation(value="登陆接口获取用户列表",httpMethod = "post")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){
         Cookie[] cookies=request.getCookies();
         User u;
         for(Cookie cookie:cookies){
             if(cookie.getName().equals("lily")&&cookie.getValue().equals("singer")&&user.getAge().equals("18")&&user.getName().equals("Kate")){
                   u=new User();
                   u.setAge("18");
                   u.setName("John");
                   u.setSex("male");
                   return u.toString();
             }

         }
        return "cookies不合法";
    }

}
