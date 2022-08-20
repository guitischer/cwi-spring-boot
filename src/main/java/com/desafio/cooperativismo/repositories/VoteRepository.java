package com.desafio.cooperativismo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cooperativismo.models.Poll;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

  Optional<Vote> findOneByUserAndPoll(User user, Poll poll);
}
