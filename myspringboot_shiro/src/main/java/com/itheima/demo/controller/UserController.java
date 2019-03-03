package com.itheima.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String Hello(){
        return "hello,world";
    }

    /**
     * 测试 thymeleaf
     */
    @RequestMapping("/thymeleafTest")
    //一定注意  这里不要加ResponseBody 这里加上会把路径变成字符串返回给页面，
    // 不加会去templates目录下招对应的html
    public String getThemleafTest(Model model){
        //1.把数据存入model
        //2.返回test.html
        model.addAttribute("name","欢迎来到登录页面");
        //3.路径在templates下的test.html
        return "test";
    }

    /**
     * 用户的添加功能
     * @return
     */
    @RequestMapping("/add")
    public String add(){

        return "user/add";
    }

    /**
     * 用户的更新功能
     * @return
     */
    @RequestMapping("/update")
    public String update(){

        return "user/update";
    }

    /**
     * 用户的更新功能
     * @return
     */
    @RequestMapping("/toLogin")
    public String login(){

        return "login";
    }


    /**
     * 用户的登录功能
     * @return
     */
    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        System.out.println("name:"+name);
        System.out.println("password:"+password);
        /**
         * 使用shiro 编写认证操作
         */
        //1.获取subject对象
        //2.封装用户名和密码
        //3.实现登录
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken(name,password);
        try {
            subject.login(token);
            //直接通过controller重定向的thymeleafTest
            model.addAttribute("name",name);
            return "test";
        }catch (UnknownAccountException e){
            //登录失败，用户不存在
            System.out.println("e:"+e);
            //model 带数据不能使用重定向
            model.addAttribute("msg","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败，密码错误
            System.out.println("e:"+e);
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

    /**
     * 用户的添加功能
     * @return
     */
    @RequestMapping("/noauth")
    public String noAuth(){

        return "noauth";
    }


}
