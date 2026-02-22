package com.gamechane.backend.game.presentation;

import com.gamechane.backend.exceptions.InvalidDataException;
import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.exceptions.http.BadRequestException;
import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.game.service.dto.GameDTO;
import com.gamechane.backend.game.service.GameService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameService gameService;

    GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public List<GameDTO> getGames(Authentication authentication) {
        return gameService.findGamesByUsername(authentication.getName());
    }

    @PostMapping("/")
    public GameDTO saveGame(Authentication authentication, @RequestBody GameDTO gameDTO) throws Exception {
        return gameService.createGame(authentication.getName(), gameDTO);
    }

    @PutMapping("/")
    public GameDTO updateGame(Authentication authentication, @RequestBody GameDTO gameDTO) throws Exception {
        return gameService.updateGame(authentication.getName(), gameDTO);
    }

    @GetMapping("/{id}")
    public GameDTO findGameById(Authentication authentication, @PathVariable Long id) throws Exception {
        try {
            return gameService.findGameByIdAndUsername(id, authentication.getName());
        } catch (NoRecordsFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (InvalidDataException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteGame(Authentication authentication, @PathVariable Long id) throws Exception {
        try {
            return gameService.deleteGame(id, authentication.getName());
        } catch (NoRecordsFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
