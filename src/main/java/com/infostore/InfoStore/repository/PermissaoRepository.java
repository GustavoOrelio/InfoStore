package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.domain.Permissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	@Query(value = "from Permissao")
	Page<Permissao> findAll(Pageable page);
}
