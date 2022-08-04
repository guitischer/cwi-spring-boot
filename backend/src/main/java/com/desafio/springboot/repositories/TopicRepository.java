package com.desafio.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.springboot.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{
  
}
