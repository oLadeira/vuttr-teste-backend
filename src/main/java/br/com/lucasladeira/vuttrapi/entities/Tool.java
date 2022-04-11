package br.com.lucasladeira.vuttrapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String link;
    private String description;

    @ElementCollection
    @CollectionTable(name = "tool_tag", //nome da tabela
            joinColumns = @JoinColumn(name = "tool_id")) //acrescenta uma coluna que referencia o id de Tool
    @Column(name = "tag") //nome do atributo
    private List<String> tags;


}
