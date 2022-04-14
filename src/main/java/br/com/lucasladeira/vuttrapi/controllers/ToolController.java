package br.com.lucasladeira.vuttrapi.controllers;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.services.ToolServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private ToolServiceImpl toolService;

    @Autowired
    private ModelMapper mapper;


    @PostMapping
    public ResponseEntity<ToolDto> createTool(@RequestBody NewToolDto tool){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(toolService.createTool(tool), ToolDto.class));
    }
}
