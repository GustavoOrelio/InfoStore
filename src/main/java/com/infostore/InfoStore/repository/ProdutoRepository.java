package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    @Query(value = "select a from Produto a where a.descricao like %?1%")
    Page<Produto> findByDescricao(String descricao, Pageable page);

    @Query(value = "select a from Produto a where a.marca.descricao like %?1%")
    Page<Produto> findByMarca(String descricao, Pageable page);

    @Query(value = "select a from Produto a where a.marca.id = ?1")
    Page<Produto> findByMarcaId(Long id, Pageable page);

    @Query(value = "select a from Produto a where a.id = ?1")
    Produto buscarPorId(Long id);

    @Query(value = "select p from Produto p where p.categoria.id = ?1")
    List<Produto> buscarProdutosCategoria(Long idCategoria);


    Page<Produto> findAll(Pageable page);

}
