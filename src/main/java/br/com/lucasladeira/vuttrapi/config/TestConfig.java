package br.com.lucasladeira.vuttrapi.config;

import br.com.lucasladeira.vuttrapi.entities.AppUser;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.AppUserRepository;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        Tool t1 = new Tool();
        t1.setId(null);
        t1.setTitle("IntelliJ");
        t1.setLink("jetbrains.com");
        t1.setDescription("IntelliJ IDEA is undoubtedly the top-choice IDE for software developers");
        t1.getTags().addAll(List.of("programming", "ide"));

        Tool t2 = new Tool();
        t2.setId(null);
        t2.setTitle("Postman");
        t2.setLink("postman.com");
        t2.setDescription("Postman is an API platform for building and using APIs");
        t2.getTags().addAll(List.of("programming", "api", "test"));

        toolRepository.saveAll(List.of(t1, t2));

        //--------------------------------------------

        AppUser u1 = new AppUser();
        u1.setId(null);
        u1.setUsername("oLadeira");
        u1.setPassword(encoder.encode("123"));
        u1.addProfile(br.com.lucasladeira.vuttrapi.entities.enums.Profile.USER);
        u1.addProfile(br.com.lucasladeira.vuttrapi.entities.enums.Profile.ADMIN);

        AppUser u2 = new AppUser();
        u2.setId(null);
        u2.setUsername("lucas");
        u2.setPassword(encoder.encode("4321"));
        u2.addProfile(br.com.lucasladeira.vuttrapi.entities.enums.Profile.USER);

        appUserRepository.saveAll(List.of(u1, u2));
    }
}
