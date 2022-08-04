package com.desafio.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.springboot.models.Poll;

public interface PollRepository extends JpaRepository<Poll, Long>{
  
}
