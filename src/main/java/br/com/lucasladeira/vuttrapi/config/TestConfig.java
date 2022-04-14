package br.com.lucasladeira.vuttrapi.config;

import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ToolRepository toolRepository;

    @Override
    public void run(String... args) throws Exception {

        Tool t1 = new Tool();
        t1.setId(null);
        t1.setTitle("IntelliJ");
        t1.setLink("jetbrains.com");
        t1.setDescription("IntelliJ IDEA is undoubtedly the top-choice IDE for software developers");
        t1.getTags().addAll(List.of("programming", "ide"));

        Tool t2 = new Tool();
        t1.setId(null);
        t1.setTitle("Postman");
        t1.setLink("postman.com");
        t1.setDescription("Postman is an API platform for building and using APIs");
        t1.getTags().addAll(List.of("programming", "api", "test"));

        toolRepository.saveAll(List.of(t1, t2));
    }
}
