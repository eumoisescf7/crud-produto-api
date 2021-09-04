package br.compasso.desafio.crudproduto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto implements Serializable {

    private static final long serialVersionUID = -3969352858203924755L;

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
