package com.infostore.InfoStore.model;

import java.util.Date;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissao")
@Getter
@Setter
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
