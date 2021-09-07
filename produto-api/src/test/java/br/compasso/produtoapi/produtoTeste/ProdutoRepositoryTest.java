package br.compasso.produtoapi.produtoTeste;

import br.compasso.produtoapi.entity.Produto;
import br.compasso.produtoapi.repository.ProdutoRepository;
import br.compasso.produtoapi.repository.ProdutoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProdutoRepositoryTest {

    private final ProdutoRepository repository;
    private final ProdutoRepositoryImpl produtoRepository;


    @Test
    public void createProduct(){

        Produto produto = Produto.builder()
                .name("Compasso")
                .description("Compasso Uol")
                .price(1000000.0)
                .build();

        repository.save(produto);

        var response = repository.findAll();

        Assertions.assertEquals(response.size(), 1);

    }


    @Test
    public void updateProduct(){

        Produto produto = Produto.builder()
                .name("XRP")
                .description("Ripple")
                .price(6.50)
                .build();

        repository.save(produto);
        Optional<Produto> response = repository.findById(1);

        Produto novoProduto = Produto.builder()
                .id(response.get().getId())
                .name("XRP")
                .description("Ripple")
                .price(6.58)
                .build();

        repository.save(novoProduto);
        Optional<Produto> responseFinal = repository.findById(1);

        Assertions.assertEquals(responseFinal.get().getPrice(), 6.58);

    }


    @Test
    public void deleteProduct(){

        Produto produto = Produto.builder()
                .name("Bitcoin")
                .description("Bitcoin description")
                .price(50000.0)
                .build();

        repository.save(produto);
        repository.delete(produto);

        var response = repository.findAll().size();
        Assertions.assertEquals(response, 0);

    }


    @Test
    public void searchProduct(){

        Produto produto = Produto.builder()
                .name("Compasso")
                .description("Compasso Uol")
                .price(100.0)
                .build();

        repository.save(produto);
        var response = produtoRepository.search("Compasso", 100.0, 101.0);

        Assertions.assertEquals(response.size(), 1);

    }

}
