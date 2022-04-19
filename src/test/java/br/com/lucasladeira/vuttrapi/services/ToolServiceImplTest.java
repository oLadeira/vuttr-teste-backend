package br.com.lucasladeira.vuttrapi.services;

import br.com.lucasladeira.vuttrapi.dto.NewToolDto;
import br.com.lucasladeira.vuttrapi.dto.ToolDto;
import br.com.lucasladeira.vuttrapi.entities.Tool;
import br.com.lucasladeira.vuttrapi.repositories.ToolRepository;
import br.com.lucasladeira.vuttrapi.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToolServiceImplTest {

    //mock data
    public static final long ID = 100L;
    public static final String TITLE = "C99297g5FP";
    public static final String LINK = "9C04yJy9";
    public static final String DESCRIPTION = "E3ORC7ZNR0SCY0";
    public static final List<String> TAGS = Arrays.asList("3FHuBo293y", "MqkCyC2y1687");

    @InjectMocks
    private ToolServiceImpl toolService;

    @Mock
    private ToolRepository toolRepository;

    @Mock
    private ModelMapper mapper;

    private Tool tool;
    private NewToolDto newToolDto;
    private ToolDto toolDto;
    private Optional<Tool> optionalTool;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startTool();
    }


    @Test
    void whenFindAllThenReturnAnListOfTools() {
        //quando chamar o metodo findAll do repository, retornar uma lista de um tool
        Mockito.when(toolRepository.findAll()).thenReturn(List.of(tool));

        List<Tool> response = toolService.getAllTools();

        //verificando se a lista nao esta nula
        Assertions.assertNotNull(response);

        //verificando se a lista possui tamanho de 1
        Assertions.assertEquals(1, response.size());

        //verificando se o item de indice 0 da lista é uma classe Tool
        Assertions.assertEquals(Tool.class, response.get(0).getClass());

        Assertions.assertEquals(ID, response.get(0).getId());
        Assertions.assertEquals(TITLE, response.get(0).getTitle());
        Assertions.assertEquals(DESCRIPTION, response.get(0).getDescription());
        Assertions.assertEquals(LINK, response.get(0).getLink());
        Assertions.assertEquals(TAGS, response.get(0).getTags());
    }

    @Test
    void getAllToolsByTag() {
    }

    @Test
    void whenFindByTagThenReturnAnObjectNotFoundException(){
        //estou forçando o repository jogar uma excecao
        Mockito.when(toolRepository.findByTags(Mockito.anyString())).thenThrow(new ObjectNotFoundException("Tag not found!"));

        try {
            //vai jogar uma excecao independente do parametro passado
            toolService.getAllToolsByTag("api");
        }catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class, ex.getClass());
            Assertions.assertEquals("Tag not found!", ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnSuccess() {

        Mockito.when(toolRepository.save(Mockito.any())).thenReturn(tool);

        Tool response = toolService.createTool(newToolDto);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getClass(), Tool.class);

        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(TITLE, response.getTitle());
        Assertions.assertEquals(DESCRIPTION, response.getDescription());
        Assertions.assertEquals(LINK, response.getLink());
        Assertions.assertEquals(TAGS, response.getTags());
    }

    @Test
    void deleteWithSuccess() {

        Mockito.when(toolRepository.findById(Mockito.anyLong())).thenReturn(optionalTool);
        Mockito.doNothing().when(toolRepository).delete(Mockito.any());
        toolService.deleteTool(ID);

        //verificando quantas vezes o delete foi invocado no repository
        Mockito.verify(toolRepository, Mockito.times(1)).delete(Mockito.any());
    }

    private void startTool(){
        tool = new Tool(ID, TITLE, LINK, DESCRIPTION, TAGS);
        toolDto = new ToolDto(ID, TITLE, LINK, DESCRIPTION, TAGS);
        newToolDto = new NewToolDto(TITLE, LINK, DESCRIPTION, TAGS);
        optionalTool = Optional.of(new Tool(ID, TITLE, LINK, DESCRIPTION, TAGS));
    }
}