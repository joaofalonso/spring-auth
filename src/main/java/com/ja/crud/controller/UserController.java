package com.ja.crud.controller;


import com.ja.crud.dto.request.DeleteCustomUserDTO;
import com.ja.crud.dto.request.CreateCustomUserDTO;
import com.ja.crud.model.CustomUser;
import com.ja.crud.security.UserDetailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserDetailServiceImpl userDetailServiceImpl;

    public UserController(UserDetailServiceImpl userDetailServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateCustomUserDTO createCustomUser){
        CustomUser user = this.userDetailServiceImpl.createUser(createCustomUser);
        URI uri = UriComponentsBuilder.fromUriString("/user/{user}").encode().buildAndExpand(user.getUserName()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody DeleteCustomUserDTO deleteUser){
        boolean userRemoved = this.userDetailServiceImpl.deleteUser(deleteUser.uuid());
        return ResponseEntity.noContent().build();
    }


}
