package br.compasso.desafio.crudproduto.service;

import br.compasso.desafio.crudproduto.entity.Produto;
import br.compasso.desafio.crudproduto.entity.dtos.ProdutoDTO;
import br.compasso.desafio.crudproduto.repository.ProdutoRepository;
import br.compasso.desafio.crudproduto.repository.ProdutoRepositoryImpl;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProdutoService {

    public static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    private final ProdutoRepository repository;
    private final ProdutoRepositoryImpl produtoRepository;
    private final ModelMapper modelMapper;


    public ResponseEntity<?> createProduct(Produto produto){

        ProdutoDTO produtoDTO = new ProdutoDTO();
        Produto response = repository.save(produto);
        modelMapper.map(response, produtoDTO);

        return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);

    }


    public ResponseEntity<?> updateProduct(Integer id, Produto produto) throws NotFoundException {

        ProdutoDTO produtoDTO = new ProdutoDTO();
        Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));

        produto.setId(id);
        modelMapper.map(repository.save(produto), produtoDTO);
        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);

    }


    public ResponseEntity<?> findProductById(Integer id) throws NotFoundException {

        Optional<Produto> response = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));
        ProdutoDTO produtoDTO = new ProdutoDTO();

        modelMapper.map(response.get(), produtoDTO);
        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);

    }


    public ResponseEntity<?> listAllProducts(){

        List<Produto> response = repository.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    public ResponseEntity<?> listAllByFilter(String q, Double min_price, Double max_price){

        List<Produto> response = new ArrayList<>();

        if ((q != null || min_price != null || max_price != null)) {
            response = produtoRepository.search(q, min_price, max_price);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    public ResponseEntity<?> deleteProduct(Integer id) throws NotFoundException {

        Optional<Produto> response = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));

        repository.delete(response.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
