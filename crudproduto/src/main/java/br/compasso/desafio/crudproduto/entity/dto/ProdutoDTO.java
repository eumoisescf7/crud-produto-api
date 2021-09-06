package br.compasso.desafio.crudproduto.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {


    private Integer id;

    private String name;

    private String description;

    private Double price;
}
