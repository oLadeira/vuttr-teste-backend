package br.com.lucasladeira.vuttrapi.controllers;

import br.com.lucasladeira.vuttrapi.dto.AppUserDto;
import br.com.lucasladeira.vuttrapi.dto.NewAppUserDto;
import br.com.lucasladeira.vuttrapi.entities.AppUser;
import br.com.lucasladeira.vuttrapi.services.AppUserServiceImpl;
import io.swagger.models.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<AppUserDto>> getAllUsers(){
        List<AppUserDto> users = appUserService.getAllUsers().stream()
                .map(user -> mapper.map(user, AppUserDto.class)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
