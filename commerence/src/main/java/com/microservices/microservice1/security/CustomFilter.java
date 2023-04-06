package com.microservices.microservice1.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomFilter extends OncePerRequestFilter{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomUserDetails customDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token=getJWTFromRequest(request);
                System.out.println("commerecence-->CustomFilter.java-->Line 28");
                if(token!=null){
                Boolean validateToken=restTemplate.postForObject("http://localhost:8080/validatetoken", token, Boolean.class);
                String inBlackList=restTemplate.postForObject("http://localhost:8080/blacklist",token, String.class);
                if(validateToken && inBlackList==null){
                String username=restTemplate.postForObject("http://localhost:8080/getusername",token, String.class);
                //UserDetails userDetails=restTemplate.postForObject("http://localhost:8080/userdetails",username,UserDetails.class);
                UserDetails userDetails=customDetails.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
    private String getJWTFromRequest(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")){
            token=token.substring(7); // "Bearer "
            return token;
        }
        return null;
    }
}
