package com.zdx.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zdx
 * @function 注册拦截器功能，使用SpringMVC则是使用配置文件
 * @date 2020/5/18 20:55
 */
//加上注解表示配置类，实现接口表示注册拦截器
@Configuration
public class AdminInterceptor implements WebMvcConfigurer {

    // 重写addInterceptors方法，通过registry对象addInterceptor方法可以添加拦截器，再使用addPathPatterns添加拦截路径，
    // 使用excludePathPatterns添加不拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
