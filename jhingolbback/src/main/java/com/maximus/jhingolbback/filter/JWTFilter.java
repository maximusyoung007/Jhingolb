package com.maximus.jhingolbback.filter;

import com.maximus.jhingolbback.shiro.JWTToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class JWTFilter extends BasicHttpAuthenticationFilter{
    private static final Logger logger = LogManager.getLogger(JWTFilter.class);

    /**
     * 登录标识
     * 如果前端发起的请求是必须登录才可以返回的，则必须携带此标识
     */
    private static String LOGIN_SIGN = "Authorization";

    /**
     * 判断用户是否想要登录
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request,ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(LOGIN_SIGN);
        return token != null;
    }

    /**
     * 对token进行检查
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,Object mappedValue) throws UnauthorizedException {
        if(isLoginAttempt(request,response)) {
            try {
                executeLogin(request,response);
                return true;
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return false;
    }

    /**
     * 执行登录操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader(LOGIN_SIGN);
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，触发shirorealm自身的登录控制，具体内容手动实现，如果错误他会抛出异常并被捕获
        getSubject(request,response).login(jwtToken);
        return true;
    }

    //异常请求跳转
    private void responseError(ServletResponse response,String message) {
        try {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            message = URLEncoder.encode(message,"UTF-8");
            httpResponse.sendRedirect("/pc/login/noLogin?message=" + message);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    }
