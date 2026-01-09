package com.gamechane.backend.skilltree.persistence;

import com.gamechane.backend.skilltree.domain.GameBlock;
import org.springframework.data.repository.CrudRepository;

public interface GameBlockRepository extends CrudRepository<GameBlock, Long> {
}
