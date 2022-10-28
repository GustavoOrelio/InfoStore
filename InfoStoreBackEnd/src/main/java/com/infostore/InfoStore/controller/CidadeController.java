package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Cidade;
import com.infostore.InfoStore.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/infostore/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Cidade> buscarTodos() {
        return cidadeService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Cidade inserir(@RequestBody Cidade cidade) {
        return cidadeService.inserir(cidade);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Cidade alterar(@RequestBody Cidade cidade) {
        return cidadeService.alterar(cidade);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        cidadeService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
