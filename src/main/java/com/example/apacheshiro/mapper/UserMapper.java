package com.example.apacheshiro.mapper;

import com.example.apacheshiro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: tkn
 * @Date: 2018/7/17 22:42
 * @Description:
 */
public interface UserMapper {
    User findByUserName(@Param("username") String username);
}
