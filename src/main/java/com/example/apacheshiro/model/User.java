package com.example.apacheshiro.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: tkn
 * @Date: 2018/7/17 22:39
 * @Description:
 */
@Data
public class User {

    private  Integer uid;

    private String username;

    private String password;

    private Set<Role> roles =new HashSet<>();
}
