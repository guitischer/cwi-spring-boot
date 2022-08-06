package com.desafio.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.springboot.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
