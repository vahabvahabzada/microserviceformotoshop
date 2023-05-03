package com.microservices.microservice1.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservices.microservice1.dtos.RoleDto;
import com.microservices.microservice1.dtos.UserDto;

@Component
public class CustomUserDetails implements UserDetailsService{

    private final RestTemplate restTemplate;
    public CustomUserDetails(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto dtoUser=restTemplate.postForObject("http://localhost:8080/userdetails", username, UserDto.class);
        return new User(username, dtoUser.getPassword(), mapRolesToAuthorities(dtoUser.getRoles()));
    }
    
    
    private List<GrantedAuthority> mapRolesToAuthorities(List<RoleDto> roles){
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for(RoleDto role:roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return grantedAuthorities;
    }
}
