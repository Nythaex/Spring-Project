package com.example.demo.conf;

import com.example.demo.web.inteceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LocaleChangeInterceptor changeInterceptor;
    private final LoginInterceptor loginInterceptor;

    public WebConfig(LocaleChangeInterceptor changeInterceptor, LoginInterceptor loginInterceptor) {
        this.changeInterceptor = changeInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(changeInterceptor);
        registry.addInterceptor(loginInterceptor);
    }
}
