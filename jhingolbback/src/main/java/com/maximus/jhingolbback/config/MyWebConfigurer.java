package com.maximus.jhingolbback.config;

//import com.maximus.jhingolbback.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    static final String[] ORIGINS = new String[] { "GET", "POST", "PUT", "DELETE" };
//    @Bean
//    public LoginInterceptor getLoginIntercepter() {
//        return new LoginInterceptor();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(getLoginIntercepter()).
//                addPathPatterns("/**").
//                excludePathPatterns("/index.html").
//                excludePathPatterns("/login/login").
//                excludePathPatterns("/article/getFirstPageArticleList");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").
                allowCredentials(true).allowedMethods(ORIGINS)
                .maxAge(3600);
    }
}
