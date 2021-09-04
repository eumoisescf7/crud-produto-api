package br.compasso.desafio.crudproduto.controller;

import br.compasso.desafio.crudproduto.entity.Produto;
import br.compasso.desafio.crudproduto.service.ProdutoService;
import io.swagger.annotations.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Api(value="REST Atendimento")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProdutoController {

    private final ProdutoService service;


    @ApiOperation(value = "Criação de um produto")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Produto criado salvo no banco de dados com sucesso!"),
            @ApiResponse(
                    code = 400,
                    message = "Alguns dados estão incorretos, verifique!."
            ),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<?> createProduct(@Valid @RequestBody Produto produto) {

            return service.createProduct(produto);

    }


    @ApiOperation(value = "Atualização de um produto")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Atualização do produto realizado no banco de dados."),
            @ApiResponse(
                    code = 404,
                    message = "Produto não existe no banco de dados"
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable("id") Integer id,
                                           @Valid @RequestBody Produto produto) throws NotFoundException {

        return service.updateProduct(id, produto);

    }


    @ApiOperation(value = "Busca de um produtos por Id")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Busca de produto realizada com sucesso!"),
            @ApiResponse(
                    code = 400,
                    message = "Alguns dados estão incorretos, verifique!."
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@Valid @PathVariable("id") Integer id) throws NotFoundException {

        return service.findProductById(id);

    }


    @ApiOperation(value = "Lista de produtos")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Lista de produtos realizada com sucesso!"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResponseEntity<?> listAllProducts(){

        return service.listAllProducts();

    }


    @ApiOperation(value = "Lista de produtos filtrados")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Lista de produtos filtrados realizada com sucesso!"),
            @ApiResponse(
                    code = 400,
                    message = "Alguns dados estão incorretos, verifique!."
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public ResponseEntity<?> listAllByFilter(@RequestParam(required = false) String q,
                                             @RequestParam(required = false) Double min_price,
                                             @RequestParam(required = false) Double max_price){

        return service.listAllByFilter(q, min_price, max_price);

    }


    @ApiOperation(value = "Deleção de um produto por id")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Produto deletado da base de dados!"),
            @ApiResponse(
                    code = 404,
                    message = "Produto não encontrado no banco de dados!"
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@Valid @PathVariable("id") Integer id) throws NotFoundException {

        return service.deleteProduct(id);

    }
}
