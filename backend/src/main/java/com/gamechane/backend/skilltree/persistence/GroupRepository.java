package com.gamechane.backend.skilltree.persistence;

import com.gamechane.backend.skilltree.domain.SkillTreeGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroupRepository extends CrudRepository<SkillTreeGroup, Long> {
    Optional<SkillTreeGroup> findByGamePin(String gamePin);
}
