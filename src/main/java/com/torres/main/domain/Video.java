package com.torres.main.domain;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "video_listed")
public class Video {
    @Id
    public String id;
    @NotBlank(message = "Titulo inválido")
    public String titulo;
    @NotBlank(message = "Descrição inválida")
    public String descricao;
    @NotBlank(message = "Url inválida")
    public String url;
}