package br.com.lucasladeira.vuttrapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDto {

    private Long id;

    private String title;
    private String link;
    private String description;

}
