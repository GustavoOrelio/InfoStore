package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
