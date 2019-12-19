package com.course.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isdelete;


    public User(){

    }
    public User(String username, String password, String age, String sex, String permission, String isdelete) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.permission = permission;
        this.isdelete = isdelete;
    }

    @Override
    public String toString(){
        return("{"+"id:"+id+","+
                "username:"+username+","+
                "password:"+password+","+
                "age:"+age+","+
                "sex:"+sex+","+
                "permission:"+permission+","+
                "isdelete:"+isdelete+"}"
        );


    }
}