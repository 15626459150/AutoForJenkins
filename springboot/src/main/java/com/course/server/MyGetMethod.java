package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value="/",description = "This is all methods for Get request")
public class MyGetMethod {
    @RequestMapping(value="/getCookies",method=RequestMethod.GET)
    @ApiOperation(value="to get cookies",httpMethod="Get")
    public String getCookies(HttpServletResponse response){
        Cookie cookie=new Cookie("lucy", "female");
        response.addCookie(cookie);
           return "恭喜你获取cookies成功";

    }
    @RequestMapping(value="/requestWithCookies",method=RequestMethod.GET)
    @ApiOperation(value="to request with cookies",httpMethod="Get")
    public String RequestWithCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return "you need to request with cookies";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("lucy")&&cookie.getValue().equals("female")){
                return "恭喜你访问成功！";
            }
        }
        return "you need to request with cookies";
    }
    @RequestMapping(value="/getWithParam1",method=RequestMethod.GET)
    @ApiOperation(value="way 1 of httpget with param",httpMethod="Get")
    public Map<String,Integer> getWithParam(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList=new HashMap<>();
        myList.put("coat", 300);
        myList.put("cookie", 30);
        myList.put("pen", 50);
        return myList;
    }

    @RequestMapping(value="/getWithParam2/{start}/{end}",method=RequestMethod.GET)
    @ApiOperation(value="way 2 of httpget with param",httpMethod="Get")
    public Map<String,Integer> myGetList(@PathVariable Integer start,@PathVariable Integer end){
       Map<String,Integer> myList=new HashMap<>();
        myList.put("coat", 300);
        myList.put("cookie", 30);
        myList.put("pen", 50);
        return myList;
    }
}
