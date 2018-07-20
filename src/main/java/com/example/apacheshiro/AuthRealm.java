package com.example.apacheshiro;

import com.example.apacheshiro.model.Permission;
import com.example.apacheshiro.model.Role;
import com.example.apacheshiro.model.User;
import com.example.apacheshiro.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: tkn
 * @Date: 2018/7/18 20:18
 * @Description: 实现自己的域
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role role : roleSet) {
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 强转成UsernamePasswordToken  验证用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByUserName(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
