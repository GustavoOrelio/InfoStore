package com.infostore.InfoStore.model;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "permissao_pessoa")
@Getter
@Setter
public class PermissaoPessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name="idPermissao")
	@JsonIgnore
	private Permissao permissao;
	@ManyToOne
	@JoinColumn(name="idPessoa")
	private Pessoa pessoa;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private  Date dataAtualizacao;

}
