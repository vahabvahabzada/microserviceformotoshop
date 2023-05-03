package com.microservices.microservice1.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.microservices.microservice1.jwt.JWTGenerator;
import com.microservices.microservice1.jwt.JwtAuthEntryPoint;
import com.microservices.microservice1.jwt.JwtFilter;
import com.microservices.microservice1.repos.BlackListRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    private JWTGenerator jwtGenerator;
    private CustomUserDetailsService userDetailsService;
    private final BlackListRepo blackListRepo;

    public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint,JWTGenerator jwtGenerator,CustomUserDetailsService userDetailsService,BlackListRepo blackListRepo){
        this.jwtAuthEntryPoint=jwtAuthEntryPoint;
        this.jwtGenerator=jwtGenerator;
        this.userDetailsService=userDetailsService;
        this.blackListRepo=blackListRepo;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .cors()
        
        .and()
        
        .csrf().disable()

        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        
        .and()
        
        .authorizeHttpRequests()
        
        .requestMatchers("/signup","/login","/userdetails","/validatetoken","/blacklist","/getusername").permitAll()
        
        .anyRequest().authenticated()
        
        .and()
        
        .httpBasic();

        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter(jwtGenerator, userDetailsService, blackListRepo);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
