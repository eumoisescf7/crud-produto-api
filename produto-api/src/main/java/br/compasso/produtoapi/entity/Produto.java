package br.compasso.produtoapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    @NotEmpty(message = "Campo nome obrigatório")
    private String name;

    @Column(name = "description", nullable = false, length = 250)
    @NotEmpty(message = "Campo descrição obrigatório")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Campo descrição obrigatório")
    @Min(value = 0)
    private Double price;

}
