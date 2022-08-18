package com.infostore.InfoStore.repository;

import com.infostore.InfoStore.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    @Query(value = "select a from Usuario a where a.nome like %?1%")
    Page<Usuario> findByNome(String nome, Pageable page);

    //Page<Usuario> findAll(Pageable page);

}
