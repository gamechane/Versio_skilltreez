package com.gamechane.backend.skilltree.presentation.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillTreeDTO {
    private Long id;
    private String name;
    private int points;
    private String description;
    private String finalMessage;
    private Boolean isPublic;
    private String username;

    private List<GroupDTO> groups = new ArrayList<>();
    private List<GameBlockDTO> gameBlocks = new ArrayList<>();

    public SkillTreeDTO(Long id, String name, int points, String description, String finalMessage, Boolean isPublic, String username){
        this.id = id;
        this.name = name;
        this.points = points;
        this.description = description;
        this.finalMessage = finalMessage;
        this.isPublic = isPublic;
        this.username = username;
    }
}

