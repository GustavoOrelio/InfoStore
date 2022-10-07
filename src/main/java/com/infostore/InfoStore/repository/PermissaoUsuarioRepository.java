package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.PermissaoPessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoPessoa, Long> {
	@Query(value = "from PermissaoPessoa")
	Page<PermissaoPessoa> findAll(Pageable page);
}
