package com.ja.crud.controller;


import com.ja.crud.dto.request.DeleteCustomUserDTO;
import com.ja.crud.dto.request.CreateCustomUserDTO;
import com.ja.crud.model.CustomUser;
import com.ja.crud.security.CustomUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private CustomUserDetailService customUserDetailService;

    public UserController(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateCustomUserDTO createCustomUser){
        CustomUser user = this.customUserDetailService.createUser(createCustomUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody DeleteCustomUserDTO deleteUser){
        boolean userRemoved = this.customUserDetailService.deleteUser(deleteUser.uuid());
        return ResponseEntity.noContent().build();
    }


}
