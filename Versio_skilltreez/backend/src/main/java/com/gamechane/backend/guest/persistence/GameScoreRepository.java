package com.gamechane.backend.guest.persistence;

import com.gamechane.backend.guest.domain.GameScore;
import com.gamechane.backend.guest.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameScoreRepository extends CrudRepository<GameScore, Long> {
    List<GameScore> getBySession(Session session);
}
