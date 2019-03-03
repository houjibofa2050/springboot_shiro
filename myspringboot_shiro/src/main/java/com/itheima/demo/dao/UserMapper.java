package com.itheima.demo.dao;

import com.itheima.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where name=#{name}")
    public User findByName(String name);

    @Select("select * from user where id=#{id}")
    public User findById(Integer id);
}
