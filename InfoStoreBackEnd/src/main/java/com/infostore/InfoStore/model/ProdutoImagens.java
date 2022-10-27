package com.infostore.InfoStore.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto_imagens")
@Getter
@Setter

public class ProdutoImagens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
