package com.ja.crud.service.authentication;

import com.ja.crud.dto.request.LoginRequest;
import com.ja.crud.dto.response.AuthTokenResponseDTO;
import com.ja.crud.model.CustomUser;
import com.ja.crud.security.UserDetailServiceImpl;
import com.ja.crud.security.jwt.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private UserDetailServiceImpl userDetailsService;

    private JwtUtil jwt;

    public AuthenticationServiceImpl(UserDetailServiceImpl userDetailsService, JwtUtil jwt) {
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
    }


    @Override
    public AuthTokenResponseDTO login(LoginRequest loginRequest) {
        boolean login = this.userDetailsService.login(loginRequest);
        if(login){
            UserDetails user =  this.userDetailsService.loadUserByUsername(loginRequest.userName());
            String token = this.jwt.createToken(user);
            return new AuthTokenResponseDTO(token);
        }
        throw new RuntimeException();
    }

}
