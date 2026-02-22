package com.gamechane.backend.student.presentation.DTO;

import com.gamechane.backend.skilltree.presentation.DTO.GameBlockDTO;
import com.gamechane.backend.skilltree.presentation.DTO.SkillTreeDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProgressDTO {
    private Long id;

    private SkillTreeDTO skillTreeDTO;
    private List<GameBlockDTO> unlockedBlocks = new ArrayList<>();

    public ProgressDTO(Long id, SkilltreeDTO skilltreeDTO, List<GameBlockDTO> unlockedBlocks){
        this.id = id;
        this.skillTreeDTO = skillTreeDTO;
        this.unlockedBlocks = unlockedBlocks;
    }
}
