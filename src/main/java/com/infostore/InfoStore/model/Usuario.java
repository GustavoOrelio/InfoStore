package com.infostore.InfoStore.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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

    @Lob
    private byte[] imagemPerfil;

    private String cpf;

    private String email;

    @Column(length = 4000)
    private String senha;

    @CreationTimestamp
    private Timestamp dataCadastro;

    @UpdateTimestamp
    private Timestamp dataModificacao;

}
