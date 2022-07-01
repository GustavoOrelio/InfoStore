package com.infostore.InfoStore.domain;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissao_usario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class PermissaoUsuario {
	private static final long serialVersionUID = 4048798961366546485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date dataAtribuicao;
	@ManyToOne
	@JoinColumn(name="idPermissao")
	private Permissao permissao;
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@CreationTimestamp
	private Timestamp dataCadastro;

	@UpdateTimestamp
	private Timestamp dataModificacao;
}
