package com.ja.crud.controller;

import com.ja.crud.dto.response.HomeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<HomeResponseDto> home(){
        HomeResponseDto serverIsUp = new HomeResponseDto("Server is up", Instant.now());
        return ResponseEntity.ok(serverIsUp);
    }

}
