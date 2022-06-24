package com.infostore.InfoStore.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @NotBlank
    @Schema(description = "Nome do produto", example = "Monitor", required = true)
    private String nome;

    @Schema(description = "Quantidade de produto", example = "15", required = true)
    private Integer quantidade;

    @Column(length = 4000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id")
    private Marca marca;


}
