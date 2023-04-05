package com.microservices.microservice1.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.microservices.microservice1.security.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomUserDetails userDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token=getJWTFromRequest(request);
        if(token!=null){
        String deprecatedToken=restTemplate.postForObject("http://localhost:8080/blacklist", token,String.class);
        Boolean tokenValidation=restTemplate.postForObject("http://localhost:8080/validatetoken",token,Boolean.class);
        if(deprecatedToken==null && tokenValidation){
            String username=restTemplate.postForObject("http://localhost:8080/getusername",token,String.class);
            UserDetails istifadeciDetallari=userDetails.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(istifadeciDetallari, null,istifadeciDetallari.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }
        filterChain.doFilter(request, response);
    }
    
    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken=request.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
            bearerToken=bearerToken.substring(7);// "Bearer "
            return bearerToken;
        }
        return null;
    }
}
