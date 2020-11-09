package com.maximus.jhingolbback.realm;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.model.Role;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.AuthUserService;
import com.maximus.jhingolbback.service.RoleService;
import com.maximus.jhingolbback.shiro.JWTToken;
import com.maximus.jhingolbback.util.JWTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LogManager.getLogger(ShiroRealm.class);
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

        String username = JWTUtil.getUsername(principals.getPrimaryPrincipal().toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据登录名获取登录用户信息
        if(!StringUtils.isEmpty(username)) {
            Result<List<Role>> roleResult = roleService.getRolesByUserName(username);
            if(roleResult != null && roleResult.getType().equals("success") && roleResult.getData().size() > 0) {
                for(Role role : roleResult.getData()) {
                    authorizationInfo.addRole(role.getRole());
                }
            }
            return authorizationInfo;
        }

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
        //String token = (String) authToken.getCredentials();
        String username = authToken.getPrincipal().toString();

        if(StringUtils.isEmpty(username)) {
            throw new AuthenticationException("token错误!");
        }
        AuthUser user = new AuthUser();
        user.setUsername(username);
        List<AuthUser> list = userService.getAuthUser(user);

        if(list.size() <= 0) {
            throw new AuthenticationException("用户不存在");
        }
        AuthUser user1 = list.get(0);
        String password = user1.getPassword();
        String salt = user1.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,password, ByteSource.Util.bytes(salt),username);
        return authenticationInfo;
    }

    /**
     * 将shiro原生token替换未JWTToken
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 清除登录用户授权信息缓存
     */
    public void clearCached() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    /**
     * 超级管理员不做判断，拥有所有角色
     */
//    @Override
//    public boolean hasRole(PrincipalCollection principalCollection,String roleIdentifier) {
//        String username = JWTUtil.getUsername(principalCollection.getPrimaryPrincipal().toString());
//        return Constant.SYSTEM_SUPER_ADMIN.equals(username) || super.hasRole(principals, roleIdentifier);
//    }
}
