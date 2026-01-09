package com.gamechane.backend.game.service;

import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.game.domain.*;
import com.gamechane.backend.game.persistence.*;
import com.gamechane.backend.game.service.dto.CardDTO;
import com.gamechane.backend.game.service.dto.GameDTO;
import com.gamechane.backend.game.service.dto.GameTypeDTO;
import com.gamechane.backend.game.service.dto.RubricDTO;
import com.gamechane.backend.util.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final CardRepository cardRepository;
    private final RubricRepository rubricRepository;
    private  final AnswerRepository answerRepository;

    public GameService(GameRepository gameRepository, CardRepository cardRepository,
                       RubricRepository rubricRepository, AnswerRepository answerRepository) {
        this.gameRepository = gameRepository;
        this.cardRepository = cardRepository;
        this.rubricRepository = rubricRepository;
        this.answerRepository = answerRepository;
    }

    public GameDTO createGame(String username, GameDTO gameDTO) throws Exception {
        Game game = ObjectMapperUtils.map(gameDTO, Game.class);
        game.setUsername(username);
        String gameType = gameDTO.getType().trim().toUpperCase();
        game.setGameType(GameType.valueOf(gameType));

        game = gameRepository.save(game);

        if (game.getCards() != null) {
            Game finalGame = game;

            game.getCards().forEach(card -> card.setGame(finalGame));
            game.getRubrics().forEach(rubric -> rubric.setGame(finalGame));

            cardRepository.saveAll(game.getCards());
            rubricRepository.saveAll(game.getRubrics());

            game.getCards().forEach(card -> {
                card.getAnswers().forEach(answer -> answer.setCard(card));
                answerRepository.saveAll(card.getAnswers());
            });
        }

        gameDTO = ObjectMapperUtils.map(game, GameDTO.class);
        gameDTO.setType(gameType);

        return gameDTO;
    }

    public GameDTO updateGame(String username, GameDTO gameDTO) throws Exception {
        Optional<Game> gameOptional = gameRepository.findByIdAndUsername(gameDTO.getId(), username);

        if (gameOptional.isEmpty()) {
            throw new NoRecordsFoundException("Cannot find game.");
        }

        Game game = ObjectMapperUtils.map(gameDTO, Game.class);

        String gameType = gameDTO.getType().trim().toUpperCase();
        game.setGameType(GameType.valueOf(gameType));
        game = gameRepository.save(game);

        List<Card> cards = cardRepository.findByGame(game);
        cardRepository.deleteAll(cards);

        List<Rubric> rubrics = rubricRepository.findByGame(game);
        rubricRepository.deleteAll(rubrics);

        cards = ObjectMapperUtils.mapAll(gameDTO.getCards(), Card.class);
        rubrics = ObjectMapperUtils.mapAll(gameDTO.getRubrics(), Rubric.class);

        Game finalGame = game;
        cards.forEach(card -> card.setGame(finalGame));
        rubrics.forEach(rubric -> rubric.setGame(finalGame));

        game.setRubrics(ObjectMapperUtils.mapAll(rubricRepository.saveAll(rubrics), Rubric.class));
        game.setCards(ObjectMapperUtils.mapAll(cardRepository.saveAll(cards), Card.class));
        game.getCards().forEach(card -> {
            card.getAnswers().forEach(answer -> answer.setCard(card));
            answerRepository.saveAll(card.getAnswers());
        });

        GameDTO newGameDTO = new GameDTO();
        newGameDTO.setId(game.getId());
        newGameDTO.setName(game.getName());
        newGameDTO.setDescription(game.getDescription());
        newGameDTO.setInformation(game.getInformation());
        newGameDTO.setCardsOpen(game.getCardsOpen());
        newGameDTO.setType(gameType);
        newGameDTO.setCards(ObjectMapperUtils.mapAll(game.getCards(), CardDTO.class));
        newGameDTO.setRubrics(ObjectMapperUtils.mapAll(game.getRubrics(), RubricDTO.class));

        return newGameDTO;
    }

    public List<GameDTO> findGamesByUsername(String username) {
        List<Game> games = gameRepository.findByUsername(username);
        List<GameDTO> gameDTOS = new ArrayList<>();

        for (Game game : games) {
            game.setCards(cardRepository.findByGame(game));
            game.setRubrics(rubricRepository.findByGame(game));

            GameDTO gameDTO = ObjectMapperUtils.map(game, GameDTO.class);
            gameDTO.setType(game.getGameType().name());
            gameDTOS.add(gameDTO);
        }

        return gameDTOS;
    }

    public GameDTO findGameByIdAndUsername(Long id, String username) throws Exception {
        Optional<Game> game = gameRepository.findByIdAndUsername(id, username);

        if (game.isEmpty()) {
            throw new NoRecordsFoundException("No game found.");
        }

        return ObjectMapperUtils.map(game.get(), GameDTO.class);
    }

    public GameDTO findGameById(Long id) throws Exception {
        Optional<Game> game = gameRepository.findById(id);

        if (game.isEmpty()) {
            throw new NoRecordsFoundException("No game found.");
        }

        return ObjectMapperUtils.map(game.get(), GameDTO.class);
    }

    public Boolean deleteGame(Long id, String username) throws Exception {
        Optional<Game> gameOptional = gameRepository.findByIdAndUsername(id, username);

        if (gameOptional.isEmpty()) {
            throw new NoRecordsFoundException("No game found.");
        }

        Game game = gameOptional.get();
        List<Card> cards = cardRepository.findByGame(game);
        List<Rubric> rubrics = rubricRepository.findByGame(game);
        rubricRepository.deleteAll(rubrics);
        List<Answer> answers = new ArrayList<>();

        cards.forEach(card -> {
            answers.addAll(card.getAnswers());
        });

        cardRepository.deleteAll(cards);
        gameRepository.delete(game);

        return true;
    }
}
