package br.com.lucasladeira.vuttrapi.controllers;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.services.ToolServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

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


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startTool();
    }

    @Test
    void whenCreateThenReturnCreatedStatusAndToolDto() {

        Mockito.when(toolService.createTool(Mockito.any())).thenReturn(tool);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(toolDto);

        ResponseEntity<ToolDto> response = toolController.createTool(newToolDto);

        Assertions.assertEquals(ToolDto.class, response.getBody().getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Assertions.assertEquals(TITLE, response.getBody().getTitle());
        Assertions.assertEquals(LINK, response.getBody().getLink());
        Assertions.assertEquals(DESCRIPTION, response.getBody().getDescription());
        Assertions.assertEquals(TAGS, response.getBody().getTags());
    }

    @Test
    void whenGetAllToolsThenReturnAListOfToolDto() {

        Mockito.when(toolService.getAllTools()).thenReturn(List.of(tool));
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(toolDto);

        ResponseEntity<List<ToolDto>> response = toolController.getAllTools();

        //verificando se o response nao e nulo
        Assertions.assertNotNull(response);

        //verificando se o corpo da resposta nao e nulo
        Assertions.assertNotNull(response.getBody());

        //verificando se o status code é == 200
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        //verificando o tipo do response
        Assertions.assertEquals(ResponseEntity.class, response.getClass());

        //verificando o tipo do body do ResponseEntity
        Assertions.assertEquals(ToolDto.class, response.getBody().get(0).getClass());
    }

    @Test
    void whenGetAllToolsByTagThenReturnAListOfToolDtoWithRespectiveTag() {

        Mockito.when(toolService.getAllToolsByTag(Mockito.anyString())).thenReturn(List.of(tool));
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(toolDto);

        ResponseEntity<List<ToolDto>> response = toolController.getToolsByTag(TAG);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(response.getBody().get(0).getTags().contains(TAG));
        Assertions.assertEquals(ToolDto.class, response.getBody().get(0).getClass());
    }

    @Test
    void whenDeleteThenReturnSuccess() {

        Mockito.doNothing().when(toolService).deleteTool(Mockito.anyLong());

        ResponseEntity<Void> response = toolController.deleteTool(ID);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Mockito.verify(toolService, Mockito.times(1)).deleteTool(Mockito.anyLong());
    }

    private void startTool(){
        tool = new Tool(ID, TITLE, LINK, DESCRIPTION, TAGS);
        toolDto = new ToolDto(ID, TITLE, LINK, DESCRIPTION, TAGS);
        newToolDto = new NewToolDto(TITLE, LINK, DESCRIPTION, TAGS);
    }
}