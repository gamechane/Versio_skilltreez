package com.gamechane.backend.guest.presentation;

import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.guest.service.GuestService;
import com.gamechane.backend.guest.presentation.DTO.GameScoreDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student/game-scores")
public class GameScoreController {
    private final GuestService gastService;

    GameScoreController(GuestService gastService) {
        this.gastService = gastService;
    }

    @GetMapping("/{sessionId}")
    public List<GameScoreDTO> vindSpelScoresMetSessie(@PathVariable Long sessionId) throws Exception {
        List<GameScoreDTO> gameScores = gastService.getGameScores(sessionId);

        if (gameScores.size() == 0) {
            throw new NotFoundException(String.format("No scores found for session: '%d'.", sessionId));
        }

        return gameScores;
    }
}
