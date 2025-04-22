package com.ja.crud.controller;

import com.ja.crud.dto.request.LoginRequest;
import com.ja.crud.dto.response.AuthTokenResponseDTO;
import com.ja.crud.security.UserDetailServiceImpl;
import com.ja.crud.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        AuthTokenResponseDTO authTokenResponseDTO = this.authenticationService.login(loginRequest);
        return ResponseEntity.ok(authTokenResponseDTO);
    }
}
