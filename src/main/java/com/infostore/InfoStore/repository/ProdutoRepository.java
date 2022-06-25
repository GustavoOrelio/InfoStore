package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.domain.Produto;
import com.infostore.InfoStore.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    @Query(value = "select a from Produto a where a.descricao like %?1%")
    Page<Produto> findByDescricao(String descricao, Pageable page);

    @Query(value = "select a from Produto a where a.marca.descricao like %?1%")
    Page<Produto> findByMarca(String descricao, Pageable page);

    @Query(value = "select a from Produto a where a.marca.id = ?1")
    Page<Produto> findByMarcaId(Long id, Pageable page);


    Page<Produto> findAll(Pageable page);

}
