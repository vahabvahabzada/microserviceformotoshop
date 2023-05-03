package com.microservices.microservice1.communication;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DBUserComController {
    @Autowired
    private DBUserComService dbUserComService;

    @PostMapping("/saveuser")
    public Boolean saveUserToDB(@RequestBody String newUser){
        dbUserComService.saveUserToDB(newUser);
        return true;
    }
}


// git congig -l
// git remote add origin TARGETURL