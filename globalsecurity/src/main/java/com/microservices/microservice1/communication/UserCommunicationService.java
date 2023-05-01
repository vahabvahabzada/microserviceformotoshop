package com.microservices.microservice1.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.microservice1.dtos.UserDto;
import com.microservices.microservice1.entities.User;
import com.microservices.microservice1.repos.UsersRepo;

@Service
public class UserCommunicationService {
    @Autowired
    private final UsersRepo repo=null;

    public UserDto findByUserName(String name) {
        User user = repo.findByUsername(name);
        UserDto dtoUser = new UserDto();
        dtoUser.setUsername(user.getUsername());
        dtoUser.setPassword(user.getPassword());
        dtoUser.setRoles(user.getRoles());
        return dtoUser;
    }
}
