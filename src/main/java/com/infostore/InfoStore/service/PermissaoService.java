package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Permissao;
import com.infostore.InfoStore.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PermissaoService {
	@Autowired
	private PermissaoRepository permissaoRepository;

	public List<Permissao> buscarTodos() {
		return permissaoRepository.findAll();
	}

	public Permissao inserir(Permissao permissao) {
		permissao.setDataCriacao(new Date());
		Permissao permissaoNovo = permissaoRepository.saveAndFlush(permissao);
		return permissaoNovo;
	}

	public Permissao alterar(Permissao permissao) {
		permissao.setDataCriacao(new Date());
		return permissaoRepository.saveAndFlush(permissao);
	}

	public void excuir(Long id) {
		Permissao permissao = permissaoRepository.findById(id).get();
		permissaoRepository.delete(permissao);
	}
}
