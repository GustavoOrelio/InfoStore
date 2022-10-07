package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Categoria;
import com.infostore.InfoStore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/")
    public List<Categoria> buscarTodos() {
        return categoriaService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Categoria inserir(@RequestBody Categoria categoria) {
        return categoriaService.inserir(categoria);
    }

    @PutMapping(value = "/")
    public Categoria alterar(@RequestBody Categoria categoria) {
        return categoriaService.alterar(categoria);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        categoriaService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
