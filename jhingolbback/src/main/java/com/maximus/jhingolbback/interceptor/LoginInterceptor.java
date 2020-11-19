//package com.maximus.jhingolbback.interceptor;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseCookie;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.time.Duration;
//
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object object) {
//        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
//            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
//            return true;
//        }
//
//        Subject subject = SecurityUtils.getSubject();
//        // 使用 shiro 验证
//        if (!subject.isAuthenticated()) {
//            return false;
//        }
//        return true;
//    }
//
//}
