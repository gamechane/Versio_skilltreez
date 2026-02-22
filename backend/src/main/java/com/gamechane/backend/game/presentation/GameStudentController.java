package com.gamechane.backend.game.presentation;

import com.gamechane.backend.exceptions.InvalidDataException;
import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.exceptions.http.BadRequestException;
import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.game.service.dto.GameDTO;
import com.gamechane.backend.game.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class GameStudentController {
    private final GameService gameService;

    public GameStudentController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/game/{gameId}")
    public GameDTO findGameById(@PathVariable Long gameId) throws Exception {
        try {
            return gameService.findGameById(gameId);
        } catch (NoRecordsFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
