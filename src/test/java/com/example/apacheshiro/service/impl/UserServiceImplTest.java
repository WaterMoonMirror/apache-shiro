package com.example.apacheshiro.service.impl;

import com.example.apacheshiro.ApacheShiroApplicationTests;
import com.example.apacheshiro.model.User;
import com.example.apacheshiro.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.Assert.*;

/**
 * @Auther: tkn
 * @Date: 2018/7/18 19:14
 * @Description:
 */
@ComponentScan
public class UserServiceImplTest extends ApacheShiroApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void findByUserName() {
        User userName = userService.findByUserName("admin");
        Assert.assertNotNull(userName);
    }
}