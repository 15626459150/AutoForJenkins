package com.course.model;

import lombok.Data;

@Data
public class Login {
    private int id;
    private String username;
    private String password;
    private String expected;
}
