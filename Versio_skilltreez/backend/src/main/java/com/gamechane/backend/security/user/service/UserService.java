package com.gamechane.backend.security.user.service;

import com.gamechane.backend.exceptions.http.BadRequestException;
import com.gamechane.backend.exceptions.http.NotFoundException;
import com.gamechane.backend.security.user.domain.Role;
import com.gamechane.backend.security.user.domain.User;
import com.gamechane.backend.security.user.persistence.UserRepository;
import com.gamechane.backend.security.user.presentation.dto.UserDTO;
import com.gamechane.backend.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public UserDTO saveUser(String username, String emailAddress, String password, String role) throws BadRequestException {
        User user = new User(username, emailAddress, password);

        switch (role.trim().toUpperCase()) { // Keep it a switch statement, because there might be more roles in the future
            case "DOCENT":
                user.addRole(Role.GAME_MASTER_FREE);
                break;
            default:
                user.addRole(Role.STUDENT);
                break;
        }

        //beveilig het wachtwoord door het te hashen met een encoder, zodat het niet letterlijk in de database opgeslagen staat
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserDTO savedUser;

        try {
            savedUser = ObjectMapperUtils.map(userRepository.save(user), UserDTO.class);
            savedUser.clearPassword();
        } catch (Exception e) {
            throw new BadRequestException(String.format("There already exists an user with the following username: '%s' or email address: '%s'.", username, emailAddress));
        }

        return savedUser;
    }

    public UserDTO createUser(UserDTO userDTO) throws BadRequestException {
        User user = ObjectMapperUtils.map(userDTO, User.class);

        //beveilig het wachtwoord door het te hashen met een encoder, zodat het niet letterlijk in de database opgeslagen staat
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserDTO savedUser;

        try {
            savedUser = ObjectMapperUtils.map(userRepository.save(user), UserDTO.class);
            savedUser.clearPassword();
        } catch (Exception e) {
            throw new BadRequestException(String.format("There already exists an user with the following username: '%s' or email address: '%s'.", userDTO.getUsername(), userDTO.getEmailAddress()));
        }

        return savedUser;
    }

    /*
    Vind alle gebruikers
     */
    public List<UserDTO> findAllUsers() {
        List<UserDTO> userDTOs = ObjectMapperUtils.mapAll(userRepository.findAll(), UserDTO.class);
        for (UserDTO userDTO : userDTOs) {
            userDTO.setPassword("");
        }
        return userDTOs;
    }

    /*
    Vind een gebruiker door middel van een gebruikersId
     */
    public UserDTO findUser (Long id) {
        UserDTO userDTO = null;
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            userDTO = ObjectMapperUtils.map(user.get(), UserDTO.class);
            userDTO.setPassword(null);
        }

        return userDTO;
    }

    /*
    Vind een gebruiker door middel van het unieke email address
     */
    public UserDTO findUserByEmailAddress (String emailAddress){
        Optional<User> user = userRepository.findByEmailAddress(emailAddress);
        UserDTO userDTO = null;

        if(user.isPresent()){
            userDTO = ObjectMapperUtils.map(user.get(), UserDTO.class);
        }

        return userDTO;
    }

    public boolean checkEmailAddressAvailability(String emailAddress) {
        return findUserByEmailAddress(emailAddress) == null;
    }

    /*
    Vind een gebruiker door middel van het unieke username
     */
    public UserDTO findUserByUsername (String username){
        Optional<User> user = userRepository.findByUsername(username);
        UserDTO userDTO = null;

        if(user.isPresent()){
            userDTO = ObjectMapperUtils.map(user.get(), UserDTO.class);
        }

        return userDTO;
    }

    public boolean checkUsernameAvailability(String username) {
        return findUserByUsername(username) == null;
    }

    /*
    Veranderd een bestaande gebruiker naar de nieuwe gegevens, via de gebruikersId
     */
    public UserDTO updateUser(UserDTO userDTO) throws NotFoundException, BadRequestException {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        //Kijk of de gebruiker die aangepast moet worden, wel bestaat, om te voorkomen dat hier fouten in ontstaan
        if (userOptional.isEmpty()) {
            throw new NotFoundException(String.format("User with id: '%d' not found.", userDTO.getId()));
        }

        if (userDTO.getPassword() == null) {
            userDTO.setPassword(userOptional.get().getPassword());
        } else {
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        }

        User user = ObjectMapperUtils.map(userDTO, User.class);
        UserDTO savedUser;

        //Probeer de gebruiker te updaten, als de email als bestaat zal er een error geworpen worden, aangezien deze uniek moet zijn.
        try {
            savedUser = ObjectMapperUtils.map(userRepository.save(user), UserDTO.class);
            savedUser.setPassword("");
        } catch (Exception e) {
            throw new BadRequestException(String.format("There already exists an user with the following email address: '%s'.", userDTO.getEmailAddress()));
        }

        return savedUser;
    }

    public boolean editPassword(String username, String newPassword) {
        boolean passwordEdited = false;

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
        }

        return passwordEdited;
    }

    /*
    verwijder een gebruiker als deze bestaat
     */
    public boolean deleteUser(UserDTO userDTO) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());

        //kijk of een gebruiker bestaat voordat we deze willen verwijderen
        if (userOptional.isEmpty()) {
            throw new NotFoundException(String.format("User with id: '%d' not found.", userDTO.getId()));
        }

        User user = userOptional.get();
        userRepository.delete(user);

        return true;
    }

    /*
    Laad gebruiker door middel van een username
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("User with username: '%s' not found.", username));
        }

        User user = userOptional.get();
        List<GrantedAuthority> roles = new ArrayList<>();

        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                roles.add(new SimpleGrantedAuthority(role.name()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }

    /*
    Laad gebruiker door middel van een emailadres
     */
    public UserDetails loadUserByEmailAddress(String emailAddress) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmailAddress(emailAddress);

        if (userOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("User with email address: '%s' not found.", emailAddress));
        }

        User user = userOptional.get();
        List<GrantedAuthority> roles = new ArrayList<>();

        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                roles.add(new SimpleGrantedAuthority(role.name()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
