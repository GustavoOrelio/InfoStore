package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.ProdutoImagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoImagensRepository extends JpaRepository<ProdutoImagens, Long> {
    public List<ProdutoImagens> findByProdutoId(Long id);
}