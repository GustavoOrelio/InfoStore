package com.infostore.InfoStore.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

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
