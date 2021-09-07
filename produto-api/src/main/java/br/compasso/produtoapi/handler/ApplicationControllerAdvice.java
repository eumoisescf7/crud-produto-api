package br.compasso.produtoapi.handler;

import br.compasso.produtoapi.dto.ErrorResponseDTO;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {


    public static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado no banco de dados!";
    public static final String CAMPO_INVALIDO = "Campo inválido, verifique!";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlePageNotFoundException(MethodArgumentNotValidException ex) {
        ErrorResponseDTO build = ErrorResponseDTO.builder()
                .status_code(HttpStatus.BAD_REQUEST.value())
                .message(CAMPO_INVALIDO)
                .build();
        return new ResponseEntity(build, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity handleException(Exception exception) {
        ErrorResponseDTO build = ErrorResponseDTO.builder()
                .status_code(HttpStatus.NOT_FOUND.value())
                .message(PRODUTO_NAO_ENCONTRADO)
                .build();
        return new ResponseEntity(build, HttpStatus.NOT_FOUND);
    }
}
