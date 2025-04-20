package com.ja.crud.infra;

import com.ja.crud.repository.CustomUserRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserInitializer implements SmartInitializingSingleton {


    private CustomUserRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void afterSingletonsInstantiated() {

    }

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
