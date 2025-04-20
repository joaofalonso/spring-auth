package com.ja.crud.controller;

import com.ja.crud.dto.response.HomeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping
    public ResponseEntity<?> resourceHome(){
        HomeResponseDto serverIsUp = new HomeResponseDto("Authenticated only !", Instant.now());
        return ResponseEntity.ok(serverIsUp);
    }
}
