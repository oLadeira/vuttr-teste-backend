package br.com.lucasladeira.vuttrapi.repositories;

import br.com.lucasladeira.vuttrapi.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> findByTags(String tag);

}
