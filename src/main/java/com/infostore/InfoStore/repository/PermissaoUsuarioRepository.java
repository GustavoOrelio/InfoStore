package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.PermissaoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
	@Query(value = "from PermissaoUsuario")
	Page<PermissaoUsuario> findAll(Pageable page);
}
