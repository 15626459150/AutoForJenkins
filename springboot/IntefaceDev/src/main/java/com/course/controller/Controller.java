package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import java.util.List;


@RestController
@Api(description = "用户管理系统API接口")
public class Controller {
    @Autowired
    private SqlSessionTemplate template;
    static Logger logger= Logger.getLogger(Controller.class);
    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        PropertyConfigurator.configure("C:\\Users\\15626\\Desktop\\IdeaProjects\\springboot\\IntefaceDev\\src\\main\\resources\\log4j.properties");
        int i=template.selectOne("login", user);

        System.out.print("hahahhahhahaha"+i);
        if(i==1){
           logger.info("登陆的用户名是"+user.getUsername());
            return true;
        }
        System.out.print("登陆的用户名是"+user.getUsername()+",密码是"+user.getPassword());
        return false;

    }
     @ApiOperation(value = "添加用户接口",httpMethod = "POST")
     @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean  addUser(HttpServletResponse response, @RequestBody User user){
        int n = template.insert("adduser", user);
        if(n>0){
            return true;
        }
        logger.info("添加用户的个数是"+n+"个");
        return false;

    }
    @ApiOperation(value = "获取用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public String  getUserInfo(HttpServletResponse response, @RequestBody User user){
        User user1= template.selectOne("getuserinfo", user.getId());
        if(user!=null){
            return user1.toString();
        }

        return "查不到用户";

    }


    @ApiOperation(value = "获取用户列表接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public List<User> getUserList(HttpServletResponse response, @RequestBody User user){
        List<User> users=template.selectList("getuserlist", user);
        System.out.println(users);
        return users;
    }
    @ApiOperation(value = "更新用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUserInfo(HttpServletResponse response, @RequestBody User user){
        int n=template.update("updateuserinfo", user);
        logger.info("更新的条目数为"+n);
        return n;
    }
}
