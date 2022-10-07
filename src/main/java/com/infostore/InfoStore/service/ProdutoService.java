package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Produto;
import com.infostore.InfoStore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public Produto inserir(Produto produto) {
        produto.setDataCriacao(new Date());
        Produto produtoNovo = produtoRepository.saveAndFlush(produto);
        return produtoNovo;
    }

    public Produto alterar(Produto produto) {
        produto.setDataCriacao(new Date());
        return produtoRepository.saveAndFlush(produto);
    }

    public void excuir(Long id) {
        Produto produto = produtoRepository.findById(id).get();
        produtoRepository.delete(produto);
    }
}
