package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewAppUserDto;
import br.com.lucasladeira.vuttrapi.entities.AppUser;
import br.com.lucasladeira.vuttrapi.entities.enums.Profile;
import br.com.lucasladeira.vuttrapi.repositories.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public AppUser createUser(NewAppUserDto user) {
        AppUser saveUser = mapper.map(user, AppUser.class);
        saveUser.setPassword(encoder.encode(saveUser.getPassword()));
        saveUser.addProfile(Profile.USER);
        //user.setPassword(encoder.encode(user.getPassword()));
        //return appUserRepository.save(mapper.map(user, AppUser.class));
        return appUserRepository.save(saveUser);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }


}
