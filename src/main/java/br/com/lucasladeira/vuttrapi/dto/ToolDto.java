package br.com.lucasladeira.vuttrapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDto {

    private Long id;

    private String title;
    private String link;
    private String description;
    private List<String> tags = new ArrayList<>();
}
