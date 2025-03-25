package com.example.test.service;

import com.example.test.entity.AuthUser;
import com.example.test.repo.AuthUserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUserRepo authUserRepo;
    private final JWTService jwtService;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, AuthUserRepo authUserRepo, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.authUserRepo = authUserRepo;
        this.jwtService = jwtService;
    }

    public AuthUser register(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        return authUserRepo.save(authUser);
    }

    public String verify(AuthUser authUser) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authUser.getUsername() , authUser.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authUser.getUsername());
        }else {
            return "failed";
        }
    }
}
