package de.unaddict.backend.controller;

import de.unaddict.backend.modules.LoginData;
import de.unaddict.backend.security.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class LoginController {

    final JWTUtils jwtUtils;
    final AuthenticationManager authenticationManager;

    public LoginController(JWTUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginData loginData) {
        try {
            //Hat Berechtigung sich anzumelden?
            final UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword());
            authenticationManager.authenticate(token);
            //Wenn ja = JWT Token wiedergeben!
            return JWTUtils.createToken(new HashMap<>(), loginData.getEmail());
        } catch (AuthenticationException e) {
            //Wenn nein = Fehlermeldung!
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid credentials!");
        }
    }
}