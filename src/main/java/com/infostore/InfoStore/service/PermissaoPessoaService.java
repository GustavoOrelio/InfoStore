package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Permissao;
import com.infostore.InfoStore.model.PermissaoPessoa;
import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.repository.PermissaoPessoaRepository;
import com.infostore.InfoStore.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PermissaoPessoaService {

	@Autowired
	private PermissaoPessoaRepository permissaoPessoaRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	public void vincularPessoaPermissaoCliente(Pessoa pessoa){
		List<Permissao> listaPermissao = permissaoRepository.findByNome("cliente");
		if (listaPermissao.size()>0){
			PermissaoPessoa permissaoPessoa = new PermissaoPessoa();
			permissaoPessoa.setPessoa(pessoa);
			permissaoPessoa.setPermissao(listaPermissao.get(0));
			permissaoPessoa.setDataCriacao((java.sql.Date) new Date());
			permissaoPessoaRepository.saveAndFlush(permissaoPessoa);
		}
	}
}
