package com.desafio.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.springboot.models.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long>{
  
}
