package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Categoria;
import com.infostore.InfoStore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/infostore/categoria")
@CrossOrigin
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> buscarTodos() {
        return categoriaService.buscarTodos();
    }

    @PostMapping("/")
    public Categoria inserir(@RequestBody Categoria objeto) {
        return categoriaService.inserir(objeto);
    }

    @PutMapping("/")
    public Categoria alterar(@RequestBody Categoria objeto) {
        return categoriaService.alterar(objeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        categoriaService.excluir(id);
        return ResponseEntity.ok().build();
    }

}