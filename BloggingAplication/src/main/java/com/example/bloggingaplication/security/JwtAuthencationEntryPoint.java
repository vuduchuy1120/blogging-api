package com.example.bloggingaplication.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthencationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //this method is called whenever any exception is thrown due to an unauthenticated user trying to access a resource that requires authentication
        //we'll just send a 401 Unauthorized response to the client
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!!!");
    }
}
