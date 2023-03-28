package com.microservices.microservice1.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.microservice1.dtos.RoleDto;
import com.microservices.microservice1.dtos.UserDto;

@Service
public class CustomUserDetails implements UserDetailsService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserDto dtoUser=restTemplate.getForObject("http://localhost:8080/userdetails", UserDto.class);
        UserDto dtoUser=restTemplate.postForObject("http://localhost:8080/userdetails", username, UserDto.class);
        System.out.println("Password is "+dtoUser.getPassword());
        //return null;
        return new User(dtoUser.getUsername(),dtoUser.getPassword(),mapRolesToAuthorities(dtoUser.getRoles()));
    }

    private List<GrantedAuthority> mapRolesToAuthorities(List<RoleDto> roles){
        List<GrantedAuthority> result=new ArrayList<>();
        for(RoleDto role:roles){
            result.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return result;
    }
}
