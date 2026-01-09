package com.gamechane.backend.student.presentation;

import com.gamechane.backend.exceptions.InvalidDataException;
import com.gamechane.backend.skilltree.presentation.DTO.SkillTreeDTO;
import com.gamechane.backend.skilltree.service.SkillTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/student")
public class StudentSkillTreeController {

    @Autowired
    private SkillTreeService skillTreeService;

    @GetMapping("/skilltree/login/{gamePin}")
    public SkillTreeDTO getSkilltree(@PathVariable String gamePin) throws Exception {
        try {
            return skillTreeService.loginWithGamePin(gamePin);
        } catch (InvalidDataException IDE){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, IDE.getMessage());
            // return null;
        }
    }
}
