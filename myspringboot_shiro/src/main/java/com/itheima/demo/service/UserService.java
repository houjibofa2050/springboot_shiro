package com.itheima.demo.service;

import com.itheima.demo.domain.User;

public interface UserService {
    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    public User findByName(String name);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    public User findById(Integer id);
}
