package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.ProdutoPreco;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoPrecoRepository extends PagingAndSortingRepository<ProdutoPreco, Long>, JpaSpecificationExecutor<ProdutoPreco> {
}
