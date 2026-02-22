package com.gamechane.backend.guest.service;

import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.guest.domain.GameScore;
import com.gamechane.backend.guest.domain.Guest;
import com.gamechane.backend.guest.domain.Session;
import com.gamechane.backend.guest.persistence.*;
import com.gamechane.backend.guest.presentation.DTO.GuestDTO;
import com.gamechane.backend.guest.presentation.DTO.ScoreNameDTO;
import com.gamechane.backend.guest.presentation.DTO.SessionDTO;
import com.gamechane.backend.guest.presentation.DTO.GameScoreDTO;
import com.gamechane.backend.util.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GuestService {
    private GuestRepository guestRepository;
    private SessionRepository sessionRepository;
    private GameScoreRepository gameScoreRepository;

    public GuestService(GuestRepository guestRepository, SessionRepository sessionRepository, GameScoreRepository gameScoreRepository) {
        this.guestRepository = guestRepository;
        this.sessionRepository = sessionRepository;
        this.gameScoreRepository = gameScoreRepository;
    }

    public List<GuestDTO> getGuestsByGamePin(String gamePin) {
        return ObjectMapperUtils.mapAll(guestRepository.findByGamePin(gamePin), GuestDTO.class);
    }

    /*
    Haal de sessie op met de meest recente timestamp van een gast
     */
    public SessionDTO getLatestSession(Long guestId) throws Exception {
        Optional<Guest> guestOptional = guestRepository.findById(guestId);

        if (guestOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No guest found with id: %d.", guestId));
        }

        Guest guest = guestOptional.get();

        Optional<Session> sessionOptional = sessionRepository.findFirstByGuestOrderByDateTimeDesc(guest);

        if (sessionOptional.isEmpty()) {
            throw new NoRecordsFoundException("No session found.");
        }

        return ObjectMapperUtils.map(sessionOptional.get(), SessionDTO.class);
    }

    /*
    Haal de sessie op met de oudste timestamp van een gast
     */
    public SessionDTO getOldestSession(Long guestId) throws Exception {
        Optional<Guest> guestOptional = guestRepository.findById(guestId);

        if (guestOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No guest found with id: %d.", guestId));
        }

        Guest guest = guestOptional.get();
        Optional<Session> sessionOptional = sessionRepository.findFirstByGuestOrderByDateTimeAsc(guest);

        if (sessionOptional.isEmpty()) {
            throw new NoRecordsFoundException("No session found.");
        }

        return ObjectMapperUtils.map(sessionOptional.get(), SessionDTO.class);
    }

    /*
    Haal alle sessies van een gast op
     */
    public List<SessionDTO> getAllSessions(Long guestId) throws Exception {
        Optional<Guest> guestOptional = guestRepository.findById(guestId);

        if (guestOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No guest found with id: %d.", guestId));
        }

        Guest guest = guestOptional.get();
        List<Session> sessions = sessionRepository.getAllByGuest(guest);
        List<SessionDTO> sessionDTOS = new ArrayList<>();

        for (Session session : sessions) {
            session.setGameScore(gameScoreRepository.getBySession(session));

            SessionDTO sessionDTO = ObjectMapperUtils.map(session, SessionDTO.class);
            sessionDTOS.add(sessionDTO);
        }
        return sessionDTOS;
    }

    /*
    Haal alle spelscores bij een sessie op
     */
    public List<GameScoreDTO> getGameScores(Long sessionId) throws Exception {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);

        if (sessionOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No session found with id: '%d'.", sessionId));
        }

        Session session = sessionOptional.get();
        List<GameScore> gameScores = gameScoreRepository.getBySession(session);

        return ObjectMapperUtils.mapAll(gameScores, GameScoreDTO.class);
    }

    /*
    Sla een score van een gast op nadat hij een arrangement heeft afgerond.
     */
    public GuestDTO createSessionEntry(GuestDTO guestDTO) {
        Optional<Guest> guestOptional = guestRepository.findByGamePinAndName(guestDTO.getGamePin(), guestDTO.getName());
        Guest guest;

        // Als de gast nog niet in de database staat voor deze game pin, dan wordt de gast opgeslagen,
        // anders zal de bestaande gast gebruikt worden om de score aan toe te kennen.
        if (guestOptional.isEmpty()) {
            guest = ObjectMapperUtils.map(guestDTO, Guest.class);
            guest = guestRepository.save(guest);
        } else {
            guest = guestOptional.get();
        }

        Guest finalGuest = guest;
        List<SessionDTO> savedSessions = new ArrayList<>();

        for (SessionDTO sessionDTO : guestDTO.getSession()) {
            Session session = ObjectMapperUtils.map(sessionDTO, Session.class);
            session.setGuest(finalGuest);
            Session savedSession = sessionRepository.save(session);

            List<GameScore> gameScores = ObjectMapperUtils.mapAll(sessionDTO.getGameScore(), GameScore.class);
            gameScores.forEach(gameScore -> gameScore.setSession(savedSession));
            List<GameScoreDTO> savedGameScores = ObjectMapperUtils.mapAll(gameScoreRepository.saveAll(gameScores), GameScoreDTO.class);

            SessionDTO savedSessionDTO = ObjectMapperUtils.map(savedSession, SessionDTO.class);
            savedSessionDTO.setGameScore(savedGameScores);
            savedSessions.add(savedSessionDTO);
        }

        GuestDTO savedGuest = ObjectMapperUtils.map(guest, GuestDTO.class);
        savedGuest.setSession(savedSessions);

        return savedGuest;
    }

    /*
    Vind de top 10 scores voor een game pin, deze scores zullen naam uniek zijn. Dus geen dubbele highscores.
     */
    public List<ScoreNameDTO> getTopTenScoresByGamePin(String gamePin) {
        List<Guest> guests = guestRepository.findByGamePin(gamePin);
        List<ScoreNameDTO> scoreNameDTOs = new ArrayList<>();

        for (Guest guest : guests) {
            List<Session> sessions = guest.getSession();
            //Sorteer de lijst van hoogste naar laagste
            sessions.sort(new Comparator<Session>() {
                public int compare(Session o1, Session o2) {
                    return o2.getTotalScore().compareTo(o1.getTotalScore());
                }
            });

            if (sessions.size() > 0) {
                Session session = sessions.get(0);
                ScoreNameDTO scoreNameDTO = new ScoreNameDTO();
                scoreNameDTO.setName(guest.getName());
                scoreNameDTO.setScore(session.getTotalScore());
                scoreNameDTOs.add(scoreNameDTO);
            }
        }

        //Sorteer de lijst van hoogste naar laagste
        scoreNameDTOs.sort(new Comparator<ScoreNameDTO>() {
            public int compare(ScoreNameDTO o1, ScoreNameDTO o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });

        //Pak de 10 hoogste, anders return de lijst als het er minder dan 10 zijn
        if (scoreNameDTOs.size() > 10) {
            return scoreNameDTOs.subList(0, 10);
        } else {
            return scoreNameDTOs;
        }
    }
}
