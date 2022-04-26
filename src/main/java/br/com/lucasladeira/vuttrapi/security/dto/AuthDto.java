package br.com.lucasladeira.vuttrapi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
