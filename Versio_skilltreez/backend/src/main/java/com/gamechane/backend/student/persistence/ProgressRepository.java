package com.gamechane.backend.student.persistence;

import com.gamechane.backend.student.domain.Progress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends CrudRepository<Progress, Long> {
    Optional<Progress> findByIdAndUserId(Long id, Long userId);

    List<Progress> findByUserId(long id);
}
