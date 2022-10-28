package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Marca;
import com.infostore.InfoStore.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Marca> buscarTodos() {
        return marcaService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Marca inserir(@RequestBody Marca marca) {
        return marcaService.inserir(marca);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Marca alterar(@RequestBody Marca marca) {
        return marcaService.alterar(marca);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        marcaService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
