package com.ja.crud.repository;

import com.ja.crud.model.CustomAuthority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomAuthorityRepository extends CrudRepository<CustomAuthority, UUID> {
}
