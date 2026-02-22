package com.gamechane.backend.guest.presentation;

import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.guest.presentation.DTO.GuestDTO;
import com.gamechane.backend.guest.service.GuestService;
import com.gamechane.backend.guest.presentation.DTO.ScoreNameDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class GuestController {
    private final GuestService guestService;

    GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/{gamePin}")
    public List<GuestDTO> findGuestsByGamePin(@PathVariable String gamePin) throws NotFoundException {
        List<GuestDTO> guests = guestService.getGuestsByGamePin(gamePin);

        if (guests.size() == 0) {
            throw new NotFoundException("No guests found.");
        }

        return guests;
    }

    @PostMapping("/")
    public GuestDTO saveScoreEntry(@RequestBody GuestDTO guestDTO) {
        return guestService.createSessionEntry(guestDTO);
    }

    @GetMapping("/{gamePin}/topten/")
    public List<ScoreNameDTO> getTopTenByGamePin(@PathVariable String gamePin) {
        return guestService.getTopTenScoresByGamePin(gamePin);
    }
}
