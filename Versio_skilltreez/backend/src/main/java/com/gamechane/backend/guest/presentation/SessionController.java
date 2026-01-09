package com.gamechane.backend.guest.presentation;

import com.gamechane.backend.exceptions.InvalidDataException;
import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.exceptions.http.BadRequestException;
import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.guest.service.GuestService;
import com.gamechane.backend.guest.presentation.DTO.SessionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student/session")
public class SessionController {
    private final GuestService guestService;

    SessionController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/{guestId}")
    public List<SessionDTO> findSessionsByGuest(@PathVariable Long guestId) throws Exception {
        List<SessionDTO> sessionDTOs = guestService.getAllSessions(guestId);

        if (sessionDTOs.size() == 0) {
            throw new NotFoundException("No sessions found.");
        }

        return sessionDTOs;
    }

    @GetMapping("/{guestId}/latest/")
    public SessionDTO findLatestSessionByGuest(@PathVariable Long guestId) throws Exception {
        try {
            return guestService.getLatestSession(guestId);
        } catch (NoRecordsFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/{guestId}/oldest/")
    public SessionDTO findOldestSessionByGuest(@PathVariable Long guestId) throws Exception {
        try {
            return guestService.getOldestSession(guestId);
        } catch (NoRecordsFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
