package com.gamechane.backend.student.presentation;

import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.student.presentation.DTO.ProgressDTO;
import com.gamechane.backend.student.service.ProgressService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/progress")
public class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/")
    public List<ProgressDTO> getProgresses(Authentication authentication) throws NoRecordsFoundException {
        return progressService.findProgressByEmailAddress(authentication.getName());
    }

    @GetMapping("/{progressId}")
    public ProgressDTO getProgressById(@PathVariable Long progressId) throws Exception {
        return progressService.getProgressById(progressId);
    }

    @PostMapping("/{gamePin}")
    public ProgressDTO addProgressWithGamePin(Authentication authentication, @PathVariable String gamePin) throws Exception {
        return progressService.addProgressWithGamePin(authentication.getName(), gamePin);
    }

    @PutMapping("/")
    public ProgressDTO saveProgress(Authentication authentication, @RequestBody ProgressDTO progressDTO) throws NoRecordsFoundException {
        return progressService.saveProgress(authentication.getName(), progressDTO);
    }

    @DeleteMapping("/{progressId}")
    public Boolean deleteProgress(Authentication authentication, @PathVariable long progressId) throws Exception {
        try {
            return progressService.deleteProgress(authentication.getName(), progressId);
        } catch (NoRecordsFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
