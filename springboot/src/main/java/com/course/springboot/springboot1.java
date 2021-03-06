package com.course.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class springboot1 {

    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "Hello world！！！！";
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(springboot1.class, args);

    }
}
