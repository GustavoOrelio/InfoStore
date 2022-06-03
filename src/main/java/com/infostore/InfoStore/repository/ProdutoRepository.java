package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    @Query(value = "select a from Produto a where a.nome like %?1%")
    Page<Produto> findByNome(String nome, Pageable page);

    Page<Produto> findAll(Pageable page);

}
