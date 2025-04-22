package com.ja.crud.security;

import com.ja.crud.dto.request.CreateCustomUserDTO;
import com.ja.crud.dto.request.LoginRequest;
import com.ja.crud.security.jwt.JwtUtil;
import com.ja.crud.model.CustomUser;
import com.ja.crud.repository.CustomUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private CustomUserRepository customUserRepository;

    private BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    public UserDetailServiceImpl(CustomUserRepository customUserRepository){
        logger.info("CustomUserDetailService constructor...");
        this.customUserRepository = customUserRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser byUserName = customUserRepository.findByUserName(username);

        if(byUserName != null){
            return User.builder()
                    .username(byUserName.getUserName())
                    .password(byUserName.getPassword())
                    .build();
        }else return null;

    }

    public boolean login(LoginRequest loginRequest) {
        CustomUser byUserName = this.customUserRepository.findByUserName(loginRequest.userName());
        return passwordEncoder.matches(loginRequest.password(),byUserName.getPassword());

    }

    public CustomUser createUser(CreateCustomUserDTO createCustomUser) {
        UserDetails userDetails = this.loadUserByUsername(createCustomUser.email());
        if(userDetails != null)
            throw new IllegalArgumentException();
        CustomUser customUser = new CustomUser(createCustomUser.email(), this.passwordEncoder.encode(createCustomUser.password()));
        createCustomUser.roles().forEach(customUser::addRole);
        return this.customUserRepository.save(customUser);
    }

    public boolean deleteUser(UUID uuid) {
        return true;
    }
}
