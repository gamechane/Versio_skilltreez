package com.gamechane.backend.guest.persistence;

import com.gamechane.backend.guest.domain.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GuestRepository extends CrudRepository<Guest, Long> {
    List<Guest> findByGamePin(String gamePin);

    Optional<Guest> findByGamePinAndName(String gamePin, String name);
}
