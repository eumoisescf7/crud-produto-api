package br.compasso.desafio.crudproduto.entity.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponseDTO {

    private Integer status_code;
    private String message;

}
