package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Categoria;
import com.infostore.InfoStore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public Categoria inserir(Categoria categoria) {
        categoria.setDataCriacao(new Date());
        Categoria categoriaNovo = categoriaRepository.saveAndFlush(categoria);
        return categoriaNovo;
    }

    public Categoria alterar(Categoria categoria) {
        categoria.setDataCriacao(new Date());
        return categoriaRepository.saveAndFlush(categoria);
    }

    public void excuir(Long id) {
        Categoria categoria = categoriaRepository.findById(id).get();
        categoriaRepository.delete(categoria);
    }
}
