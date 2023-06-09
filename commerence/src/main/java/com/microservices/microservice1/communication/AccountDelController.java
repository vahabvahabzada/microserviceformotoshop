package com.microservices.microservice1.communication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountDelController {
    private AccountDelService accountDelService;
    public AccountDelController(AccountDelService accountDelService){
        this.accountDelService=accountDelService;
    }

    @PostMapping("/deleteaccounturl")
    public Boolean deleteAccount(@RequestBody String username){
        System.out.println("from AccontDelController : "+username);
        System.out.println("AccountDelController-->"+accountDelService.deleteAccount(username));
        return true;
    }
}
