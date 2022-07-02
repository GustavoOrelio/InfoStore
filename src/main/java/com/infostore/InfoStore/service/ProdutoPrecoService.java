package com.infostore.InfoStore.service;


import com.infostore.InfoStore.domain.ProdutoPreco;
import com.infostore.InfoStore.repository.ProdutoPrecoRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class ProdutoPrecoService {
    @Autowired
    private ProdutoPrecoRepository produtoPrecoRepository;

    public ProdutoPreco save(ProdutoPreco produtoPreco){
        return produtoPrecoRepository.save(produtoPreco);
    }



}
