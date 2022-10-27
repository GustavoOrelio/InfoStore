package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Cidade;
import com.infostore.InfoStore.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> buscarTodos() {
        return cidadeRepository.findAll();
    }

    public Cidade inserir(Cidade cidade) {
        cidade.setDataCriacao(new Date());
        Cidade cidadeNovo = cidadeRepository.saveAndFlush(cidade);
        return cidadeNovo;
    }

    public Cidade alterar(Cidade cidade) {
        cidade.setDataCriacao(new Date());
        return cidadeRepository.saveAndFlush(cidade);
    }

    public void excuir(Long id) {
        Cidade cidade = cidadeRepository.findById(id).get();
        cidadeRepository.delete(cidade);
    }
}
