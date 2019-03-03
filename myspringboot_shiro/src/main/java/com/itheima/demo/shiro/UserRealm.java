package com.itheima.demo.shiro;

import com.itheima.demo.domain.User;
import com.itheima.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //1.创建资源授权的对象

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        //2.获取当前用户
        //3.去数据库查询获得授权字符串
        //4.给资源授权对象中添加字符串

        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User userNew = userService.findById(user.getId());
        simpleAuthorizationInfo.addStringPermission(userNew.getPerms());

        return simpleAuthorizationInfo;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行身份认证逻辑");
//
//        String name="admin";
//        String password="123456";


        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)authenticationToken;
        String name=usernamePasswordToken.getUsername();
        User user = userService.findByName(name);


        System.out.println("用户名："+usernamePasswordToken.getUsername());
        System.out.println("密码："+usernamePasswordToken.getPassword());
        if(!usernamePasswordToken.getUsername().equals(name)){
            // 用户名错误，shiro 底层会抛出UnKnowAcountException
            return null;
        }
        System.out.println("用户名："+usernamePasswordToken.getUsername());
        System.out.println("密码："+usernamePasswordToken.getPassword());
        //第二个参数是数据库的密码
        //Object principal,  这里的
        //Object credentials,
        //String realmName
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
