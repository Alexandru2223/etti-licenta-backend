package com.akhianand.springrolejwt.controller;

import com.akhianand.springrolejwt.config.TokenProvider;
import com.akhianand.springrolejwt.mapper.UserEntityToUserDTO;
import com.akhianand.springrolejwt.model.AuthToken;
import com.akhianand.springrolejwt.model.LoginUser;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.model.UserDto;
import com.akhianand.springrolejwt.repository.UserRepository;
import com.akhianand.springrolejwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    private final UserRepository repository;

    private final UserEntityToUserDTO userEntityToUserDTO;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public UserEntity saveUser(@Valid @RequestBody UserDto user){
        return userService.save(user);
    }

    @GetMapping("/{user}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable String user){
        return userEntityToUserDTO.convert(repository.findByUsername(user));
    }

    @PutMapping("/{user}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateUser(@RequestBody UserDto userDto, @PathVariable String user){
        UserEntity userEntity = repository.findByUsername(user);
        userEntity.setName(userDto.getName());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setBirthdate(userDto.getBirthdate());
        repository.save(userEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }

}
