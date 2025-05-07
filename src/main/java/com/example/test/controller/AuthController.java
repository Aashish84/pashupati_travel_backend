package com.example.test.controller;

import com.example.test.entity.AuthUser;
import com.example.test.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthUser register(@RequestBody AuthUser authUser) {
        return authService.register(authUser);
    }

    @PostMapping("/isLogin")
    public String isLogin(@RequestBody AuthUser authUser) {
        return authService.verify(authUser);
    }
}
