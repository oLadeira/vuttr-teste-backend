package br.com.lucasladeira.vuttrapi.repositories;

import br.com.lucasladeira.vuttrapi.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
