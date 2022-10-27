package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Estado;
import com.infostore.InfoStore.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> buscarTodos() {
        return estadoRepository.findAll();
    }

    public Estado inserir(Estado estado) {
        estado.setDataCriacao(new Date());
        Estado estadoNovo = estadoRepository.saveAndFlush(estado);
        return estadoNovo;
    }

    public Estado alterar(Estado estado) {
        estado.setDataCriacao(new Date());
        return estadoRepository.saveAndFlush(estado);
    }

    public void excuir(Long id) {
        Estado estado = estadoRepository.findById(id).get();
        estadoRepository.delete(estado);
    }
}
