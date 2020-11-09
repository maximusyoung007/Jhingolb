package com.maximus.jhingolbback.service.Impl;

import com.maximus.jhingolbback.exception.MyException;
import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.service.AuthUserService;
import com.maximus.jhingolbback.service.LoginService;
import com.maximus.jhingolbback.util.JWTUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LogManager.getLogger(LoginServiceImpl.class);
    @Resource
    private AuthUserService userService;

    @Override
    public String login(String username,String password) {
        AuthUser user = new AuthUser();
        AuthUser user1 = userService.getAuthUser(user).get(0);
        String salt = user1.getSalt();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();

        if(!encodedPassword.equals(user1.getPassword())) {
            try{
                throw new MyException("900");
            } catch (MyException me) {
                logger.error(me);
            }
        }

        return JWTUtil.sign(username,password);

    }
}
