package com.desafio.cooperativismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.cooperativismo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
