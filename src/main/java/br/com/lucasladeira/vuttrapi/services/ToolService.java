package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;

import java.util.List;

public interface ToolService {

    List<Tool> getAllTools();

    List<Tool> getAllToolsByTag(String tag);

    Tool createTool(NewToolDto tool);

    void deleteTool(Long id);

}
