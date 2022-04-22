package br.com.lucasladeira.vuttrapi.entities;

import br.com.lucasladeira.vuttrapi.entities.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();


    public Set<Profile> getProfiles(){
        return profiles.stream()
                .map(Profile::toEnum).collect(Collectors.toSet()); //usando method reference
                //.map(x -> Profile.toEnum(x)).collect(Collectors.toSet()); usando lambda
    }

    public void addProfile(Profile profile){
        profiles.add(profile.getCod());
    }
}
