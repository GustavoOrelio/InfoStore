package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Permissao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

	@Query(value = "select p from Permissao p where p.descricao = ?1")
	public List<Permissao> buscarPermissaoNome(String descricao);

	@Query(value = "from Permissao")
	Page<Permissao> findAll(Pageable page);
}
