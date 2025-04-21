package com.ja.crud.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CustomAuthority {

    @Id
    private UUID id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser user;

    public CustomAuthority(){

    }

    public CustomAuthority(String authority){
        this.id = UUID.randomUUID();
        this.authority = authority;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
