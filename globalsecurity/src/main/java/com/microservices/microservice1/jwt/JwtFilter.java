package com.microservices.microservice1.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservices.microservice1.repos.BlackListRepo;
import com.microservices.microservice1.security.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    JWTGenerator jwtGenerator;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    BlackListRepo blackListRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String token=getJwtFromRequest(request);
                System.out.println("Yoxlama : "+blackListRepo.findByToken(token));//yoxlama

                if(token!=null && jwtGenerator.validateToken(token) && blackListRepo.findByToken(token)==null){
                    String username=jwtGenerator.getUsernameFromToken(token);
                    UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request, response);
    }
    
    private String getJwtFromRequest(HttpServletRequest request){
        String token=request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")){
            return token.substring(7);// "Bearer "
        }
        return null;
    }
}
