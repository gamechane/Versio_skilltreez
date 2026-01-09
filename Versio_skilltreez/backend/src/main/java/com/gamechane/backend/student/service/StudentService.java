package com.gamechane.backend.student.service;

import com.gamechane.backend.exceptions.NoRecordsFoundException;
import com.gamechane.backend.security.user.domain.User;
import com.gamechane.backend.security.user.persistence.UserRepository;
import com.gamechane.backend.security.user.presentation.AuthenticationController;
import com.gamechane.backend.security.user.presentation.dto.UserDTO;
import com.gamechane.backend.security.user.service.UserService;
import com.gamechane.backend.util.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationController authenticationController;

    public StudentService(
            UserService userService,
            AuthenticationController authenticationController,
            UserRepository userRepository){
        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationController = authenticationController;
    }

    public UserDTO getUserByUsername(String username) throws Exception{
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No student found with username: '%s'.", username));
        }
        return ObjectMapperUtils.map(userOptional.get(), UserDTO.class);
    }

    public UserDTO getUserByEmailAddress(String emailAddress) throws Exception{
        Optional<User> userOptional = userRepository.findByUsername(emailAddress);
        if (userOptional.isEmpty()) {
            throw new NoRecordsFoundException(String.format("No student found with email address: '%s'.", emailAddress));
        }
        return ObjectMapperUtils.map(userOptional.get(), UserDTO.class);
    }
}
