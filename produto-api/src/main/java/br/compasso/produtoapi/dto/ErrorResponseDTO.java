package br.compasso.produtoapi.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponseDTO {

    private Integer status_code;
    private String message;

}
