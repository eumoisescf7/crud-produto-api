package br.compasso.desafio.crudproduto.service;

import br.compasso.desafio.crudproduto.entity.Produto;
import br.compasso.desafio.crudproduto.entity.dto.ProdutoDTO;
import br.compasso.desafio.crudproduto.repository.ProdutoRepository;
import br.compasso.desafio.crudproduto.repository.ProdutoRepositoryImpl;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public ProdutoDTO createProduct(Produto produto){

        ProdutoDTO produtoDTO = new ProdutoDTO();
        Produto response = repository.save(produto);

        modelMapper.map(response, produtoDTO);

        return produtoDTO;

    }


    public ProdutoDTO updateProduct(Integer id, Produto produto) throws NotFoundException {

        ProdutoDTO produtoDTO = new ProdutoDTO();
        Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));

        produto.setId(id);
        modelMapper.map(repository.save(produto), produtoDTO);

        return produtoDTO;

    }


    public ProdutoDTO findProductById(Integer id) throws NotFoundException {

        Optional<Produto> produto = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));
        ProdutoDTO produtoDTO = new ProdutoDTO();

        modelMapper.map(produto.get(), produtoDTO);

        return produtoDTO;

    }


    public List<Produto> listAllProducts(){

        List<Produto> produtoList = repository.findAll();

        return produtoList;

    }


    public List<Produto> listAllByFilter(String q, Double min_price, Double max_price){

        List<Produto> produtoList = new ArrayList<>();

        if ((q != null || min_price != null || max_price != null)) {
            produtoList = produtoRepository.search(q, min_price, max_price);
        }

        return produtoList;

    }


    public void deleteProduct(Integer id) throws NotFoundException {

        Optional<Produto> produto = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));

        repository.delete(produto.get());

    }
}
