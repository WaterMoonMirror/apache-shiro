package com.example.apacheshiro.service.impl;

import com.example.apacheshiro.mapper.UserMapper;
import com.example.apacheshiro.model.User;
import com.example.apacheshiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tkn
 * @Date: 2018/7/17 22:45
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
