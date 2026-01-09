package com.gamechane.backend.guest.presentation.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SessionDTO implements Serializable {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean feedBack;
    private List<GameScoreDTO> gameScore;
    private Integer totalScore;
}
