package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import br.com.lucasladeira.vuttrapi.services.exceptions.ObjectNotFoundException;
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
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }

    @Override
    public List<Tool> getAllToolsByTag(String tag) {
        List<Tool> tools = toolRepository.findByTags(tag);

        if (tools.isEmpty()){
            throw new ObjectNotFoundException("Tag not found!");
        }
        return toolRepository.findByTags(tag);
    }

    @Override
    public Tool createTool(NewToolDto tool) {
        return toolRepository.save(mapper.map(tool, Tool.class));
    }

    @Override
    public void deleteTool(Long id) {
        Tool tool = toolRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Tool not found!"));
        toolRepository.delete(tool);
    }



}
