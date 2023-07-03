package com.example.bloggingaplication.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /* get token*/
        String requestToken = request.getHeader("Authorization");

        System.out.println(requestToken);

        String username = null;

        String token = null;

        if(requestToken!=null && requestToken.startsWith("Bearer ")){
            token = requestToken.substring(7);
            try{
                username = jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                System.out.println("unable to get token");
            }catch (ExpiredJwtException e){
                System.out.println("jwt expired");
            }catch (MalformedJwtException e){
                System.out.println("jwt is invalid");
            }
        }   else {
            System.out.println("JWT token does not begin with bearer string");
        }
        //validate token
        //set user in security context

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if(this.jwtTokenHelper.validateToken(token, userDetails)){
                //token is valid
                //setting user in security context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                System.out.println("Token is not valid");
            }

        }
        else{
            System.out.println("username is null or security context has authentication");
        }
        filterChain.doFilter(request, response);
    }
}
