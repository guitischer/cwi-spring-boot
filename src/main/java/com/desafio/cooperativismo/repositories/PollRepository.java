package com.desafio.cooperativismo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.Topic;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
  @Query(value = "SELECT p FROM Poll p WHERE p.topic = :topicId AND p.endAt > :todayDatetime")
  List<Poll> findAllOpenedPollsByTopic(@Param("topicId") Topic topic,
      @Param("todayDatetime") LocalDateTime todayDatetime);
}
