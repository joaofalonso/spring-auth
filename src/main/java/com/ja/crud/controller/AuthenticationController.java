package com.ja.crud.controller;

import com.ja.crud.dto.request.CreateCustomUserDTO;
import com.ja.crud.dto.request.LoginRequest;
import com.ja.crud.model.CustomUser;
import com.ja.crud.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String login = this.customUserDetailService.login(loginRequest);
        return ResponseEntity.ok(login);
    }
}
