package br.com.lucasladeira.vuttrapi.controllers;

import br.com.lucasladeira.vuttrapi.dto.AppUserDto;
import br.com.lucasladeira.vuttrapi.dto.NewAppUserDto;
import br.com.lucasladeira.vuttrapi.services.AppUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserServiceImpl appUserService;

    @Autowired
    private ModelMapper mapper;


    @PostMapping
    public ResponseEntity<AppUserDto> createUser(@RequestBody NewAppUserDto user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.map(appUserService.createUser(user), AppUserDto.class));
    }

}
