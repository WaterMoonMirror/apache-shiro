package com.example.apacheshiro.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: tkn
 * @Date: 2018/7/17 22:37
 * @Description:
 */
@Data
public class Role {
    private Integer rid;
    private String rname;
    private Set<Permission> permissions =new HashSet<>();
    private Set<User> users =new HashSet<>();
}
