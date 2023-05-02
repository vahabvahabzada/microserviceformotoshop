package com.microservices.microservice1.security;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.microservices.microservice1.entities.Role;
import com.microservices.microservice1.entities.User;
import com.microservices.microservice1.repos.UsersRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UsersRepo repoLogin;
    public CustomUserDetailsService(UsersRepo usersRepo){
        this.repoLogin=usersRepo;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user=repoLogin.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        List<GrantedAuthority> result=new ArrayList<>();
        for(Role role:roles){
            result.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return result;
    }
}
