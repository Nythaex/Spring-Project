package com.example.demo.web.inteceptors;

import com.example.demo.services.StatService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private StatService statService;

    public LoginInterceptor(StatService statService) {
        this.statService = statService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          statService.onRequest();
        return true;
    }
}
