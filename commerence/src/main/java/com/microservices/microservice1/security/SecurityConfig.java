package com.microservices.microservice1.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling()

                .authenticationEntryPoint(authEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/listbrands", "/saveuser").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // .addFilterBefore(new CustomFilter(),
                // UsernamePasswordAuthenticationFilter.class); // promoy burada new-lamaq her
                // request de CustomFilter-in yeni instance-ini yaradir,ona gore de meqsedeuygun
                // deyil
                .httpBasic();

        http.addFilterBefore(customFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}