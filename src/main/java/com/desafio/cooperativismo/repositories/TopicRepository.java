package com.desafio.cooperativismo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.cooperativismo.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
