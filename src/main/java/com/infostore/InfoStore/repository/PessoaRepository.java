package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
