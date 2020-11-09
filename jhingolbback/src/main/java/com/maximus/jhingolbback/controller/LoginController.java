package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.AuthUserService;
import com.maximus.jhingolbback.service.LoginService;
import com.maximus.jhingolbback.util.JWTUtil;
import com.sun.xml.internal.txw2.output.ResultFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("login")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Resource
    private AuthUserService userService;

    @Resource
    private LoginService loginService;

    /**
     * 登录认证
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<String> login(@RequestBody AuthUser user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);
            return Result.success("登录成功");
        } catch (AuthenticationException exception) {
            logger.info("账号或密码错误",exception);
            return Result.error("账号或密码错误");
        }
    }
}
