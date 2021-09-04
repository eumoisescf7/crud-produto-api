package br.compasso.desafio.crudproduto.entity.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponseDTO {

    private Integer status_code;
    private String message;

}
