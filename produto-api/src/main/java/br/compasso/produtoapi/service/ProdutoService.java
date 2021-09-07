package br.compasso.produtoapi.service;

import br.compasso.produtoapi.dto.ProdutoDTO;
import br.compasso.produtoapi.entity.Produto;
import br.compasso.produtoapi.repository.ProdutoRepository;
import br.compasso.produtoapi.repository.ProdutoRepositoryImpl;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    public static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    private final ProdutoRepository repository;
    private final ProdutoRepositoryImpl produtoRepository;
    private final ModelMapper modelMapper;


    public ProdutoDTO createProduct(Produto produto){

        Produto response = repository.save(produto);

        return modelMapper.map(response, ProdutoDTO.class);

    }


    public ProdutoDTO updateProduct(Integer id, Produto produto) throws NotFoundException {

        Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));

        produto.setId(id);

        return modelMapper.map(repository.save(produto), ProdutoDTO.class);

    }


    public ProdutoDTO findProductById(Integer id) throws NotFoundException {

        Optional<Produto> produto = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUTO_NAO_ENCONTRADO)));

        return modelMapper.map(produto.get(), ProdutoDTO.class);

    }


    public List<Produto> listAllProducts(){

        return repository.findAll();

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
