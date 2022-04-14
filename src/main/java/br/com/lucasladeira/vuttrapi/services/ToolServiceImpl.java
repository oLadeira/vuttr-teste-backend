package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService{

    @Autowired
    private ToolRepository toolRepository;

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
        return toolRepository.save(fromDTO(tool));
    }

    @Override
    public void deleteTool(Long id) {

    }


    Tool fromDTO(NewToolDto toolDto){
        Tool tool = new Tool();
        tool.setId(null);
        tool.setDescription(toolDto.getDescription());
        tool.setTitle(toolDto.getTitle());
        tool.setLink(toolDto.getLink());

        toolDto.getTags().forEach(tag -> {
            tool.getTags().add(tag);
        });

/*        for (String tag : toolDto.getTags()){
            tool.getTags().add(tag);
        }*/
        return tool;
    }



}
