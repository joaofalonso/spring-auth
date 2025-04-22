package com.ja.crud.service.authentication;

import com.ja.crud.dto.request.LoginRequest;
import com.ja.crud.dto.response.AuthTokenResponseDTO;

public interface AuthenticationService {

    AuthTokenResponseDTO login(LoginRequest loginRequest);

}
