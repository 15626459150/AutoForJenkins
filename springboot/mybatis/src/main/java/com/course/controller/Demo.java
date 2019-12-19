package com.course.controller;

import com.course.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController

public class Demo {
    @Autowired
    SqlSessionTemplate template;
    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public int getCount(){
        return  template.selectOne("getCount");
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        return  template.update("updateUser",user);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        int result=template.insert("addUser", user);
        return result;
    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public int deleteUser(@RequestParam int id){
        return  template.delete("deleteUser",id);
    }
}
