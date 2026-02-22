package com.gamechane.backend.game.persistence;

import com.gamechane.backend.game.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByUsername(String username);

    Optional<Game> findByIdAndUsername(Long id, String username);

    boolean deleteGameByIdAndUsername(Long id, String username);
}
