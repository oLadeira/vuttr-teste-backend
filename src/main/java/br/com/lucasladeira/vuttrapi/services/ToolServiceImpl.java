package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService{

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ToolDto> getAllTools() {
        return null;
    }

    @Override
    public List<ToolDto> getAllToolsByTag(String tag) {
        return null;
    }

    @Override
    public Tool createTool(NewToolDto tool) {
        return toolRepository.save(mapper.map(tool, Tool.class));
    }

    @Override
    public void deleteTool(Long id) {

    }



}
