package com.itheima.demo.service.impl;

import com.itheima.demo.dao.UserMapper;
import com.itheima.demo.domain.User;
import com.itheima.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// 在实现类上写@Service
@Service
@Component
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        User user = userMapper.findByName(name);
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }
}
