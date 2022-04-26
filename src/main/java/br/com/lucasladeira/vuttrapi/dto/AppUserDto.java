package br.com.lucasladeira.vuttrapi.dto;

import br.com.lucasladeira.vuttrapi.entities.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private Long id;

    private String username;

    private List<Profile> profiles;
}
