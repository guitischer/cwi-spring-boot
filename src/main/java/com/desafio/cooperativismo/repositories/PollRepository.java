package com.desafio.cooperativismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.cooperativismo.models.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

}
