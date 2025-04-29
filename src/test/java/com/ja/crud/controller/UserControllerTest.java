package com.ja.crud.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ja.crud.dto.request.CreateCustomUserDTO;
import com.ja.crud.model.CustomUser;
import com.ja.crud.security.UserDetailServiceImpl;
import com.ja.crud.security.filter.JwtFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {UserController.class})
//@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserDetailServiceImpl mockedUserDetailSvc;

    @MockitoBean
    private JwtFilter jwtFilter;

    @Test
    @WithMockUser
    public void givenAValidUserRequestWhenPostShouldReturn201() throws Exception {

        CreateCustomUserDTO createUsetDto = new CreateCustomUserDTO("user@email.com", "supersecret");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String requestBody = objectMapper.writeValueAsString(createUsetDto);

        when(this.mockedUserDetailSvc.createUser(createUsetDto)).thenReturn(new CustomUser(createUsetDto.email(), ""));
        doNothing().when(this.jwtFilter).doFilterInternal(any(),any(),any());

        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

}
