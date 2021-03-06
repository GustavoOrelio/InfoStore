package com.infostore.InfoStore.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "produto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4000)
    private String descricao;

    @Schema(description = "Quantidade de produto", example = "15", required = true)
    private Integer quantidadeEstoque;

    private Double valorVenda;

    private Double valorCusto;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @CreationTimestamp
    private Timestamp dataCadastro;

    @UpdateTimestamp
    private Timestamp dataModificacao;

}
