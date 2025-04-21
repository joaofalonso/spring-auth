package com.ja.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class CustomUser {

    @Id
    private UUID id;

    private String userName;
    private String password;

    @OneToMany
    private List<CustomAuthority> authorities;

    public CustomUser(){

    }

    public CustomUser(String login, String password) {
        this.id = UUID.randomUUID();
        this.userName = login;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String login) {
        this.userName = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<CustomAuthority> authorities) {
        this.authorities = authorities;
    }


    @Override
    public String toString() {
        return getId() + " - " + getUserName();
    }

    public void addRole(String s) {
        CustomAuthority customAuthority = new CustomAuthority(s);
        this.authorities.add(customAuthority);
    }
}
