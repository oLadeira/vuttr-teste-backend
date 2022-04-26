package br.com.lucasladeira.vuttrapi.repositories;

import br.com.lucasladeira.vuttrapi.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

}
