package com.infostore.InfoStore.model;

import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "produto")
@Getter
@Setter

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descricaoCurta;
    private String descricaoDetalhada;
    private Double valorCusto;
    private Double valorVenda;
    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;
    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
