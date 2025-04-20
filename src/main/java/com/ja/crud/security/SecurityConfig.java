package com.ja.crud.security;

import com.ja.crud.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    JwtFilter jwtFilter;
    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtFilter jwtFilter){
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{

        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authorize) ->
                {
                    authorize.requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("admin");
                    authorize.requestMatchers(HttpMethod.POST, "/user/**").permitAll();
                    authorize.requestMatchers("/auth/login").permitAll();
                    authorize.requestMatchers("/home").permitAll();
                    authorize.requestMatchers("/swagger-ui/**").permitAll();
                    authorize.requestMatchers("/v3/api-docs/**").permitAll();
                    authorize.requestMatchers("/swagger-ui.html").permitAll();
                    authorize.requestMatchers("/resource/**").authenticated();

                }
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
