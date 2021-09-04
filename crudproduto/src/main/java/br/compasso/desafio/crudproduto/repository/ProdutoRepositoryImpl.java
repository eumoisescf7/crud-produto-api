package br.compasso.desafio.crudproduto.repository;

import br.compasso.desafio.crudproduto.entity.Produto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProdutoRepositoryImpl {


    private final EntityManager entityManager;

    public ProdutoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Produto> search(String q, Double min_price, Double max_price){

        String query = "select p from Produto as p ";
        String condicao = "where";

        if(q != null){
            query += condicao + " p.name = :q ";
            condicao = "or";
        }

        if(q != null){
            query += condicao + " p.description = :q ";
            condicao = "and";
        }

        if(min_price != null){
            query += condicao + " p.price >= :min_price ";
            condicao = "and";
        }

        if(max_price != null){
            query += condicao + " p.price <= :max_price ";
            condicao = "and";
        }

        var queryFinal = entityManager.createQuery(query, Produto.class);

        if(q != null){
            queryFinal.setParameter("q", q);
        }

        if(min_price != null){
            queryFinal.setParameter("min_price", min_price);

        }

        if(max_price != null){
            queryFinal.setParameter("max_price", max_price);

        }
        return queryFinal.getResultList();
    }
}
