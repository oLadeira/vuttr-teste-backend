package br.com.lucasladeira.vuttrapi.security.controllers;

import br.com.lucasladeira.vuttrapi.security.dto.AuthDto;
import br.com.lucasladeira.vuttrapi.security.entities.UserDetailsSS;
import br.com.lucasladeira.vuttrapi.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody AuthDto authDto){
        UsernamePasswordAuthenticationToken authData = authDto.convert();
        try {
            Authentication authentication = authenticationManager.authenticate(authData);

            UserDetailsSS userDetails = (UserDetailsSS) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails.getUsername());
            System.out.println(token);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
