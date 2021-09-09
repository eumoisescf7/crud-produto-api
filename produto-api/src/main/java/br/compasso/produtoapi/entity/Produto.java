package br.compasso.produtoapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

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
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @Column(name = "description", nullable = false, length = 250)
    @NotBlank(message = "Campo descrição é obrigatório")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(message = "Campo preço é obrigatório")
    @Min(value = 0, message = "deve ser maior ou igual a zero")
    private Double price;

}
