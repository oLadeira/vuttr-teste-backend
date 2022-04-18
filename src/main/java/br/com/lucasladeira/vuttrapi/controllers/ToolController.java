package br.com.lucasladeira.vuttrapi.controllers;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import br.com.lucasladeira.vuttrapi.services.ToolServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolServiceImpl toolService;

    @Autowired
    private ModelMapper mapper;


    @PostMapping
    @ApiOperation(value = "Create new tool",
                  notes = "Save a new tool",
                  response = ToolDto.class)
    public ResponseEntity<ToolDto> createTool(@RequestBody NewToolDto tool){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(toolService.createTool(tool), ToolDto.class));
    }

    @ApiOperation(value = "Find all tools",
            notes = "Provide all tools stored in the database.",
            response = ToolDto.class)
    @GetMapping
    public ResponseEntity<List<ToolDto>> getAllTools(){
        List<ToolDto> tools = toolService.getAllTools()
                .stream().map(
                        tool -> mapper.map(tool, ToolDto.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(tools);
    }

    @ApiOperation(value = "Find a tool by tag",
            notes = "Search tools by the given tag",
            response = ToolDto.class)
    @GetMapping("/find")
    public ResponseEntity<List<ToolDto>> getToolsByTag(@RequestParam String tag){

        List<ToolDto> tools = toolService.getAllToolsByTag(tag)
                .stream().map(
                        tool -> mapper.map(tool, ToolDto.class)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(tools);
    }

    @ApiOperation(value = "Delete a tool by id",
            notes = "Delete a tool by id",
            response = void.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable Long id){
        toolService.deleteTool(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
