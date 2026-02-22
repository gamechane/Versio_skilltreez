package com.gamechane.backend.game.persistence;

import com.gamechane.backend.game.domain.Card;
import com.gamechane.backend.game.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByGame(Game game);

    Optional<Card> findById(Long id);

    void deleteByGameId(Long id);

    void deleteById(Long id);
}
