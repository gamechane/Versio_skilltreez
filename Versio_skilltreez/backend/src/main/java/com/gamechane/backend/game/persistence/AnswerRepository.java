package com.gamechane.backend.game.persistence;

import com.gamechane.backend.game.domain.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {}
