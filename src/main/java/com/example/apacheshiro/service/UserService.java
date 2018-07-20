package com.example.apacheshiro.service;

import com.example.apacheshiro.model.User;

/**
 * @Auther: tkn
 * @Date: 2018/7/17 22:44
 * @Description:
 */
public interface UserService {
    User findByUserName(String username);
}
