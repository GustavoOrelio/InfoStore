package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Produto;
import com.infostore.InfoStore.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/infostore/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Produto> buscarTodos(){
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public Produto buscarPorId(@PathVariable("id") Long id){
        return produtoService.buscarPorId(id);
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Produto inserir(@RequestBody Produto objeto){
        return produtoService.inserir(objeto);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Produto alterar(@RequestBody Produto objeto){
        return produtoService.alterar(objeto);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        produtoService.excluir(id);
        return ResponseEntity.ok().build();
    }

}