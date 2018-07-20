package com.example.apacheshiro.config;

import com.example.apacheshiro.AuthRealm;
import com.example.apacheshiro.CredentialMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;

/**
 * @Auther: tkn
 * @Date: 2018/7/20 21:13
 * @Description:
 */
public class ShiroConfiguration {
    /**
     *
     *  添加过滤规则
     * @param manager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc"); // 需要验证权限
        filterChainDefinitionMap.put("/login", "anon"); // 不需要权限验证
        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/admin", "roles[admin]");  //需要admin角色权限
        filterChainDefinitionMap.put("/edit", "perms[edit]");  // 需要edit 权限
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/**", "user"); // 需要用户登录验证
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    /**
     *  向验证器中注入自定义的域
     * @param authRealm
     * @return
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    /**
     *  添加自定义的域 并使用自定义的 用户验证配置
     * @param matcher
     * @return
     */
    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     *  向spring中使用自定义的用户验证配置
     * @return
     */

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    /**
     *
     *  使用域拦截验证
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     *  开启过滤拦截
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
