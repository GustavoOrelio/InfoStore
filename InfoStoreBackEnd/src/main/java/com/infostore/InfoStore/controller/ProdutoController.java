package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Produto;
import com.infostore.InfoStore.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/")
    public List<Produto> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Produto inserir(@RequestBody Produto produto) {
        return produtoService.inserir(produto);
    }

    @PutMapping(value = "/")
    public Produto alterar(@RequestBody Produto produto) {
        return produtoService.alterar(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        produtoService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
