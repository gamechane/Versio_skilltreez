package com.gamechane.backend.security.user.presentation;

import com.gamechane.backend.security.user.presentation.dto.CreateUserRequestDTO;
import com.gamechane.backend.security.user.presentation.dto.EditPasswordRequestDTO;
import com.gamechane.backend.security.user.presentation.dto.RegisterRequestDTO;
import com.gamechane.backend.security.user.presentation.dto.UserDTO;
import com.gamechane.backend.security.user.service.UserService;
import com.gamechane.backend.security.JwtTokenUtil;
import com.gamechane.backend.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        String username = registerRequestDTO.username;
        String emailAddress = registerRequestDTO.emailAddress;
        String password = registerRequestDTO.password;
        String role = registerRequestDTO.role;

        return userService.saveUser(username, emailAddress, password, role);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @GetMapping("/email-address/availability/{emailAddress}")
    public boolean checkEmailAddressAvailability(@PathVariable String emailAddress) {
        return userService.checkEmailAddressAvailability(emailAddress);
    }

    @GetMapping("/username/availability/{username}")
    public boolean checkUsernameAvailability(@PathVariable String username) {
        return userService.checkUsernameAvailability(username);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return userService.createUser(ObjectMapperUtils.map(createUserRequestDTO, UserDTO.class));
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PatchMapping("/password")
    @RolesAllowed({"ADMIN", "GAME_MASTER_PREMIUM", "GAME_MASTER_FREE", "STUDENT"})
    public boolean editPassword(@RequestBody EditPasswordRequestDTO editPasswordRequestDTO) {
        String username = editPasswordRequestDTO.username;
        String currentPassword = editPasswordRequestDTO.currentPassword;
        String newPassword = editPasswordRequestDTO.newPassword;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, currentPassword));
        } catch (AuthenticationException authenticationException) {
            return false;
        }

        return userService.editPassword(username, newPassword);
    }

    @DeleteMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteUser(@RequestBody UserDTO userDTO) {
        return userService.deleteUser(userDTO);
    }

}
