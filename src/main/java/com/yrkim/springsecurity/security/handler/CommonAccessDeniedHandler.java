package com.yrkim.springsecurity.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("commonAccessDeniedHandler")
public class CommonAccessDeniedHandler implements AccessDeniedHandler {

    /*
    * TODO 추후 ajax 호출 관련해서 access 할시 error 리턴
    * */
    /*private String errorPage;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();*/

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String ajaxHeader = request.getHeader("ajax_call");
        String result = "";

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
    }
}
