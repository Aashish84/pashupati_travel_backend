package com.example.test.service;

import com.example.test.entity.AuthUser;
import com.example.test.entity.SecurityUser;
import com.example.test.repo.AuthUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    private final AuthUserRepo authUserRepo;

    public MyUserDetailService(AuthUserRepo authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepo.findByUsername(username);
        if (authUser == null) {
            log.error("USER not found of username: {}", username);
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUser(authUser);
    }
}
