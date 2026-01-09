package com.gamechane.backend.skilltree.persistence;

import com.gamechane.backend.skilltree.domain.Connection;
import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {
}
