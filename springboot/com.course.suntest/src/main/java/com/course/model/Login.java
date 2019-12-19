package com.course.model;

import lombok.Data;

@Data
public class Login {
    Integer id;
    String username;
    String password;
    String expected;
}
