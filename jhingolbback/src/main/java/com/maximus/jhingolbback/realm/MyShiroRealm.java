package com.maximus.jhingolbback.realm;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.Role;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.AuthUserService;
import com.maximus.jhingolbback.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LogManager.getLogger(MyShiroRealm.class);
    @Autowired
    private RoleService roleService;

    @Resource
    private AuthUserService userService;

    /**
     * 为当前登录用户授予角色和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("doGetAuthorizationInfo:" + principals.toString());
        return null;
    }

    /**
     * 验证当前登录的用户
     * 根据token中的用户名从数据库中获取密码、盐等信息
     * @param authToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        String username = authToken.getPrincipal().toString();
        AuthUser user = new AuthUser();
        user.setUsername(username);
        AuthUser user1 = userService.getAuthUser(user).get(0);
        String password = user1.getPassword();
        String salt = user1.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password,ByteSource.Util.bytes(salt),getName());
        return authenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

}
