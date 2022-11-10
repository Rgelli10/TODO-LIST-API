package com.caseitau.controller;

import com.caseitau.config.TokenService;
import com.caseitau.controller.form.LoginForm;
import com.caseitau.dto.TokenDto;
import com.caseitau.entity.User;
import com.caseitau.entity.UserProfile;
import com.caseitau.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TokenDto> auth(
            @RequestBody
            @Validated LoginForm form){
        UsernamePasswordAuthenticationToken loginData = form.converter();

        try {
            Authentication authentication = authManager.authenticate(loginData);
            User byUsername = userRepository.findByUsername(form.getUsername());
            String token = tokenService.generateToken(form.getUsername(), authentication, byUsername.getProfile());

            return ResponseEntity.ok(new TokenDto(token, "Bearer")) ;
        }
        catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
