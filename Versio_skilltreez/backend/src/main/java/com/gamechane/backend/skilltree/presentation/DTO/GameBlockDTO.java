package com.gamechane.backend.skilltree.presentation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameBlockDTO {
    private Long id;
    private String name;
    private int requiredPoints;
    private int valueInPoints;
    private boolean isMystery;
    private Long gameId;
    private Long blockId;
    private List<ConnectionDTO> connections = new ArrayList<>();

    public GameBlockDTO(Long id, String name, int requiredPoints, int valueInPoints, boolean isMystery, Long gameId, Long blockId){
        this.id = id;
        this.name = name;
        this.requiredPoints =  requiredPoints;
        this.valueInPoints =valueInPoints;
        this.isMystery = isMystery;
        this.gameId = gameId;
        this.blockId = blockId;
    }
}
