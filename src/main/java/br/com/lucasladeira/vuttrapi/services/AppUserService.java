package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewAppUserDto;
import br.com.lucasladeira.vuttrapi.entities.AppUser;

import java.util.List;

public interface AppUserService {

    AppUser createUser(NewAppUserDto user);

    List<AppUser> getAllUsers();

}
