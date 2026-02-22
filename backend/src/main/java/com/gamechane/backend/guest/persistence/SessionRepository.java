package com.gamechane.backend.guest.persistence;

import com.gamechane.backend.guest.domain.Guest;
import com.gamechane.backend.guest.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> getAllByGuest(Guest gast);

    Optional<Session> findFirstByGuestOrderByDateTimeDesc(Guest guest);

    Optional<Session> findFirstByGuestOrderByDateTimeAsc(Guest guest);

    Optional<Session> findFirstByGuestOrderByTotalScoreDesc(Guest guest);
}
