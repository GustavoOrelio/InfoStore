package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaClienteRepository extends JpaRepository<Pessoa, Long> {
}
