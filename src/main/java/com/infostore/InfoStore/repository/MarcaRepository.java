package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.domain.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarcaRepository extends PagingAndSortingRepository<Marca, Long>, JpaSpecificationExecutor<Marca> {

    @Query(value = "select a from Marca a where a.descricao like %?1%")
    Page<Marca> findByDescricao(String descricao, Pageable page);

    Page<Marca> findAll(Pageable page);

}
