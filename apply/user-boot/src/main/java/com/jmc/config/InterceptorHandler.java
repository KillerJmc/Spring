package com.jmc.config;

import com.jmc.config.interceptor.SecureInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class InterceptorHandler implements WebMvcConfigurer {
    private SecureInterceptor secureInterceptor;

    @Autowired
    public void setSecureInterceptor(SecureInterceptor secureInterceptor) {
        this.secureInterceptor = secureInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var securePattens = List.of("/func", "/func.html", "/viewAllUsers",
                "/logout", "/modifyUser", "/delete", "/update");
        registry.addInterceptor(secureInterceptor).addPathPatterns(securePattens);
    }
}
