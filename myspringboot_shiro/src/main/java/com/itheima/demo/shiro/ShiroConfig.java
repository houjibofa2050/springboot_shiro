package com.itheima.demo.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 1.创建shirofilterFactorBean
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        System.out.println("=========设置过滤器=============");
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();


        /**
         * shiro 内置的过滤器
         *  anon: 无需认证登录，可以直接访问
         *  authc： 必须认证可以访问
         *  user： 使用Remmerber me 功能可以直接访问
         *  perms: 该资源必须得到权限才可以访问
         *  roles: 该角色必须得到权限才可以访问
         */

        //设置自定义的过滤器
        Map<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        /**
         * 对新增页面和修改页面进行登录的验证
         */
//        filterChainDefinitionMap.put("/add","authc");
//        filterChainDefinitionMap.put("/update","authc");
        /**
         * 对除thymeleaftest页面之外的其他页面进行身份验证，thymeleaftest页面 不进行验证
         */
        //身份认证
        filterChainDefinitionMap.put("/thymeleafTest","anon");
        filterChainDefinitionMap.put("/login","anon");

        //用户授权
        filterChainDefinitionMap.put("/add","perms[user:add]");
        filterChainDefinitionMap.put("/update","perms[user:update]");

        //身份认证
        filterChainDefinitionMap.put("/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //自定义没有身份认证的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        //自定义没有授权的授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");

        return shiroFilterFactoryBean;
    }
    /**
     * 2.创建defaultwebsecuritymanager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }
    /**
     * 3.创建Reaml
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * thymeleaf整合shiro
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
