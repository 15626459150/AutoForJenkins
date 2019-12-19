package com.course.model;

import lombok.Data;

@Data
public class AddUser {
    private String username;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String expected;
    private String isdelete;

}
