package com.infostore.InfoStore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import sun.security.util.Password;

@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Schema(description = "Nome do usuario", example = "João", required = true)
    private String nome;

    private String cpf;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    //Precisa realizar a criptografia
    private String senha;

}