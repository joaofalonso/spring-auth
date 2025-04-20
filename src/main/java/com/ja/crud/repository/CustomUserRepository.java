package com.ja.crud.repository;

import com.ja.crud.model.CustomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomUserRepository extends CrudRepository<CustomUser, UUID> {

    CustomUser findByUserName(String username);
}
