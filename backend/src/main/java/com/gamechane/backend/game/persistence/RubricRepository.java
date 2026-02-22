package com.gamechane.backend.game.persistence;

import com.gamechane.backend.game.domain.Game;
import com.gamechane.backend.game.domain.Rubric;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RubricRepository extends CrudRepository<Rubric, Long> {
    List<Rubric> findByGame(Game game);

    void deleteByGameId(Long id);
}
