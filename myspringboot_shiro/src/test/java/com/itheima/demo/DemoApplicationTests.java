package com.itheima.demo;

import com.itheima.demo.dao.UserMapper;
import com.itheima.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test(){
		String name="admin";
		User user = userMapper.findByName(name);
		System.out.println(user);
	}

	@Test
	public void test2(){
		Integer id=1;
		User user = userMapper.findById(1);
		System.out.println(user);
	}


}
