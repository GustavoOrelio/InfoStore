package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long>, JpaSpecificationExecutor<Categoria> {

    @Query(value = "select a from Categoria a where a.descricao like %?1%")
    Page<Categoria> findByDescricao(String descricao, Pageable page);

    Page<Categoria> findAll(Pageable page);

}
