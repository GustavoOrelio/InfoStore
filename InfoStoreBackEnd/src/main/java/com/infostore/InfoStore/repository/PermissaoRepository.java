package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    List<Permissao> findByNome(String nome);
}
