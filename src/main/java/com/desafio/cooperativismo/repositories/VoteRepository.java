package com.desafio.cooperativismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cooperativismo.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
