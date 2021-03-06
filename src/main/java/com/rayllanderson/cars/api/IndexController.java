package com.rayllanderson.cars.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get(){
        return ("Car api. Please use this url for start: /api/v1.0/cars");
    }

    @GetMapping("user-info")
    public UserDetails getUserInfo(@AuthenticationPrincipal UserDetails user){
        return user;
    }
}
