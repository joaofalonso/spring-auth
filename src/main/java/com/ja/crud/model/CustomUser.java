package com.ja.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CustomUser {

    @Id
    private UUID id;

    private String userName;
    private String password;

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


    @Override
    public String toString() {
        return getId() + " - " + getUserName();
    }
}
