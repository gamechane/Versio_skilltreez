package com.gamechane.backend.student.service;

import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.security.user.domain.User;
import com.gamechane.backend.security.user.persistence.UserRepository;
import com.gamechane.backend.student.domain.Progress;
import com.gamechane.backend.student.persistence.ProgressRepository;
import com.gamechane.backend.student.presentation.DTO.ProgressDTO;
import com.gamechane.backend.skilltree.domain.SkillTree;
import com.gamechane.backend.skilltree.domain.GameBlock;
import com.gamechane.backend.skilltree.persistence.SkillTreeRepository;
import com.gamechane.backend.skilltree.persistence.GameBlockRepository;
import com.gamechane.backend.skilltree.presentation.DTO.GameBlockDTO;
import com.gamechane.backend.util.ObjectMapperUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final SkillTreeRepository skillTreeRepository;
    private final GameBlockRepository gameBlockRepository;

    public ProgressService(
            ProgressRepository progressRepository, UserRepository userRepository, SkillTreeRepository skillTreeRepository, GameBlockRepository gameBlockRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.skillTreeRepository = skillTreeRepository;
        this.gameBlockRepository = gameBlockRepository;
    }

    public ProgressDTO getProgressById(Long id) throws NoRecordsFoundException{
        Optional<Progress> optionalProgress = progressRepository.findById(id);

        if (optionalProgress.isEmpty()){
            throw new NoRecordsFoundException(String.format("No progress found with id: '%d'.", id));
        }
        return ObjectMapperUtils.map(optionalProgress.get(), ProgressDTO.class);
    }

    public List<ProgressDTO> findProgressByEmailAddress(String emailAddress) {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);

        if (userOptional.isEmpty()) throw new UsernameNotFoundException(String.format("No user found with email address: '%s'.", emailAddress));

        User student = userOptional.get();
        long id = student.getId();
        List<Progress> progresses = progressRepository.findByUserId(id);
        List<ProgressDTO> progressDTOs = new ArrayList<>();

        // Loop door de gevonden progressies heen en koppel de bijbehorende objecten.
        for (Progress progress : progresses) {
            progressDTOs.add(ObjectMapperUtils.map(progress, ProgressDTO.class));
        }

        return progressDTOs;
    }

    public ProgressDTO addProgressWithGamePin(String emailAddress, String gamePin) throws NoRecordsFoundException {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);
        Optional<SkillTree> skillTreeOptional = Optional.empty();

        for (SkillTree skillTree : skillTreeRepository.findAll()) {
            if (skillTree.hasGamePin(gamePin)) {
                skillTreeOptional = skillTreeRepository.findById(skillTree.getId());
                break;
            }
        }

        if (skillTreeOptional.isEmpty()) throw new NoRecordsFoundException(String.format("Skill tree with game pin: '%s' not found.", gamePin));
        if (userOptional.isEmpty()) throw new UsernameNotFoundException(String.format("User with email address: '%s' not found.", emailAddress));

        Progress progress = new Progress(skillTreeOptional.get());
        progress.setUser(userOptional.get());
        progressRepository.save(progress);

        return ObjectMapperUtils.map(progress, ProgressDTO.class);
    }

    public ProgressDTO saveProgress(String emailAddress, ProgressDTO progressDTO) throws NoRecordsFoundException {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);

        if (userOptional.isEmpty()) throw new UsernameNotFoundException(String.format("User with email address: '%s' not found.", emailAddress));

        Optional<Progress> progressOptional = progressRepository.findByIdAndUserId(progressDTO.getId(), userOptional.get().getId());

        if (progressOptional.isEmpty()) throw new NoRecordsFoundException(String.format("Progress with id: '%d' and email address: '%s' not found.", progressDTO.getId(), emailAddress));

        List<GameBlockDTO> unlockedBlocks = progressDTO.getUnlockedBlocks();

        progressOptional = progressRepository.findById(progressDTO.getId());

        if (progressOptional.isEmpty()) throw new NoRecordsFoundException(String.format("Progress with id: '%d' not found.", progressDTO.getId()));

        Progress progress = progressOptional.get();

        progress.setUnlockedBlocks(new ArrayList<>());

        for (GameBlockDTO gameBlockDTO : unlockedBlocks) {
            Optional<GameBlock> gameBlockOptional = gameBlockRepository.findById(gameBlockDTO.getId());

            if (gameBlockOptional.isEmpty()) throw new NoRecordsFoundException(String.format("Game block with id: '%d' not found.", gameBlockDTO.getId()));

            progress.addUnlockedBlock(gameBlockOptional.get());
        }

        progressRepository.save(progress);
        progressDTO.setUnlockedBlocks(unlockedBlocks);

        return progressDTO;
    }

    public Boolean deleteProgress(String emailAddress, Long progressId) throws NoRecordsFoundException {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with email address: '%s' not found.", emailAddress));
        }

        Long userId = userOptional.get().getId();

        Optional<Progress> progressOptional = progressRepository.findByIdAndUserId(progressId, userId);

        if (progressOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("Progress with id: '%d' not found.", progressId));
        }

        progressOptional = progressRepository.findById(progressId);

        if (progressOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("Progress with id: '%d' not found.", progressId));
        }

        Progress progress = progressOptional.get();

        progressRepository.delete(progress);

        return true;
    }
}
