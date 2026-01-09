package com.gamechane.backend.security.user.presentation;

import com.gamechane.backend.security.user.presentation.dto.AuthenticationRequestDTO;
import com.gamechane.backend.security.user.service.JwtDTO;
import com.gamechane.backend.security.user.service.UserService;
import com.gamechane.backend.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
        String usernameEmailAddress = authenticationRequestDTO.usernameEmailAddress;
        String password = authenticationRequestDTO.password;
        UserDetails userDetails;

        if (!usernameEmailAddress.contains("@")) {
            userDetails = userService.loadUserByUsername(usernameEmailAddress);
        } else {
            userDetails = userService.loadUserByEmailAddress(usernameEmailAddress);
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final String token = createAuthenticationToken(userDetails);

        return ResponseEntity.ok(new JwtDTO(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> refreshToken(Authentication authentication) {
        final UserDetails userDetails = userService
                .loadUserByUsername(authentication.getName());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtDTO(token));
    }

    private String createAuthenticationToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }
}
