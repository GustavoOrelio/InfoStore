package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Estado;
import com.infostore.InfoStore.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping(value = "/")
    public List<Estado> buscarTodos() {
        return estadoService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Estado inserir(@RequestBody Estado estado) {
        return estadoService.inserir(estado);
    }

    @PutMapping(value = "/")
    public Estado alterar(@RequestBody Estado estado) {
        return estadoService.alterar(estado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        estadoService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
