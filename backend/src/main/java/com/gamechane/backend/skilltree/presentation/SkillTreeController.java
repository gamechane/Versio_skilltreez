package com.gamechane.backend.skilltree.presentation;

import Utils.Utils;
import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.skilltree.presentation.DTO.SkillTreeDTO;
import com.gamechane.backend.skilltree.service.SkillTreeService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skilltree")
public class SkillTreeController {
    private final SkillTreeService skillTreeService;

    public SkillTreeController(SkillTreeService skillTreeService) {
        this.skillTreeService = skillTreeService;
    }

    @GetMapping("/")
    public List<SkillTreeDTO> getSkillTrees(Authentication authentication) {
        return skillTreeService.findSkillTreeByUsername(authentication.getName());
    }

    @GetMapping("/{skillTreeId}")
    public SkillTreeDTO getSkillTree(@PathVariable Long skillTreeId) throws Exception {
        return skillTreeService.findSkillTreeById(skillTreeId);
    }

    @PostMapping("/")
    public SkillTreeDTO createSkillTree(Authentication authentication, @RequestBody SkillTreeDTO skillTreeDTO) {
        try{
            return skillTreeService.createSkillTree(authentication.getName(), skillTreeDTO);
        } catch (Exception e){
            Utils.writeErrorLog(skillTreeDTO.toString() + e);
            throw(e);
        }
    }

    @PutMapping("/")
    public SkillTreeDTO updateSkillTree(Authentication authentication, @RequestBody SkillTreeDTO skillTreeDTO) throws Exception {
        return skillTreeService.updateSkillTree(authentication.getName(), skillTreeDTO);
    }

    @GetMapping("/game/{gameId}")
    public Boolean checkIfGameExists(Authentication authentication, @PathVariable Long gameId){
        return skillTreeService.gameExistsInSkillTree(authentication.getName(), gameId);
    }

    @DeleteMapping("/{skillTreeId}")
    public Boolean deleteSkillTree(Authentication authentication, @PathVariable Long skillTreeId) throws Exception{
        try {
            return skillTreeService.deleteSkillTree(authentication.getName(), skillTreeId);
        } catch (NoRecordsFoundException e){
            throw new NotFoundException(e.getMessage());
        }
    }
}
