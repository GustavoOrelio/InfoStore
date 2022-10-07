package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Marca;
import com.infostore.InfoStore.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> buscarTodos() {
        return marcaRepository.findAll();
    }

    public Marca inserir(Marca marca) {
        marca.setDataCriacao(new Date());
        Marca marcaNovo = marcaRepository.saveAndFlush(marca);
        return marcaNovo;
    }

    public Marca alterar(Marca marca) {
        marca.setDataCriacao(new Date());
        return marcaRepository.saveAndFlush(marca);
    }

    public void excuir(Long id) {
        Marca marca = marcaRepository.findById(id).get();
        marcaRepository.delete(marca);
    }
}
