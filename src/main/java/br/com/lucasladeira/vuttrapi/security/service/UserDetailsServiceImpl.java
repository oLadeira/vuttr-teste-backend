package br.com.lucasladeira.vuttrapi.security.service;

import br.com.lucasladeira.vuttrapi.entities.AppUser;
import br.com.lucasladeira.vuttrapi.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found!: " + username);
        }
        return user.get();
    }
}
