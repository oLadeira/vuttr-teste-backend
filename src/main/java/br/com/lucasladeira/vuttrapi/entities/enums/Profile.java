package br.com.lucasladeira.vuttrapi.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Profile implements GrantedAuthority {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int cod;
    private String description;


    public static Profile toEnum(Integer cod){

        if (cod == null){
            return null;
        }

        for (Profile x : Profile.values()){

            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Cod inválido: " + cod);
    }


    @Override
    public String getAuthority() {
        return description;
    }
}
