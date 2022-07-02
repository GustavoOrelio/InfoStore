package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.domain.ProdutoPreco;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoPrecoRepository extends PagingAndSortingRepository<ProdutoPreco, Long>, JpaSpecificationExecutor<ProdutoPreco> {
}
