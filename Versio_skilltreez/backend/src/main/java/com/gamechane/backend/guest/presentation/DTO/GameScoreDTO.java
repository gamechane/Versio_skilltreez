package com.gamechane.backend.guest.presentation.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameScoreDTO implements Serializable {
    private Long id;
    private Integer time;
    private Integer score;
    private long gameId;
}
