package br.com.lucasladeira.vuttrapi.controllers;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.services.ToolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToolControllerTest {

    public static final long ID = 100L;
    public static final String TITLE = "C99297g5FP";
    public static final String LINK = "9C04yJy9";
    public static final String DESCRIPTION = "E3ORC7ZNR0SCY0";
    public static final List<String> TAGS = Arrays.asList("3FHuBo293y", "MqkCyC2y1687");
    public static final String TAG = "MqkCyC2y1687";

    @InjectMocks
    private ToolController toolController;

    @Mock
    private ToolServiceImpl toolService;

    @Mock
    private ModelMapper mapper;


    private Tool tool;
    private NewToolDto newToolDto;
    private ToolDto toolDto;
    private Optional<Tool> optionalTool;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startTool();
    }

    @Test
    void createTool() {
    }

    @Test
    void getAllTools() {
    }

    @Test
    void getToolsByTag() {
    }

    @Test
    void deleteTool() {
    }

    private void startTool(){
        tool = new Tool(ID, TITLE, LINK, DESCRIPTION, TAGS);
        toolDto = new ToolDto(ID, TITLE, LINK, DESCRIPTION, TAGS);
        newToolDto = new NewToolDto(TITLE, LINK, DESCRIPTION, TAGS);
    }
}