package com.ja.crud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ja.crud.dto.request.CreateCustomUserDTO;
import com.ja.crud.model.CustomUser;
import com.ja.crud.security.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerIT {


}
