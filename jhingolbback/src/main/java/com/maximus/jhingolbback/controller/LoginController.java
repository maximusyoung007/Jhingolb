package com.maximus.jhingolbback.controller;

import com.maximus.jhingolbback.model.AuthUser;
import com.maximus.jhingolbback.result.Result;
import com.maximus.jhingolbback.service.AuthUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Resource
    private AuthUserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<String> login(AuthUser user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);
            return Result.success("登录成功");
        } catch (IncorrectCredentialsException exception) {
            logger.error("密码错误",exception);
            return Result.error("密码错误");
        } catch(UnknownAccountException e) {
            logger.error("用户名不存在",e);
            return Result.error("用户名不存在");
        }
    }

    /**
     * 登出
     */
    @GetMapping("logout")
    @ResponseBody
    public Result<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success("成功登出");
    }

    /**
     * 认证，每次跳转路由之前，访问这个controller，进入拦截器，如果当前是已经登录的状态，再跳转路由
     */
    @GetMapping("authentication")
    @ResponseBody
    public Result<String> authentication() {
        return Result.success("成功","成功");
    }
}
