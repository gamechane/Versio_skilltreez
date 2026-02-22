package com.gamechane.backend.skilltree.persistence;

import com.gamechane.backend.skilltree.domain.SkillTree;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SkillTreeRepository extends CrudRepository<SkillTree, Long> {
    List<SkillTree> findByUsername(String username);

    Optional<SkillTree> findByIdAndUsername(Long id, String username);
}


