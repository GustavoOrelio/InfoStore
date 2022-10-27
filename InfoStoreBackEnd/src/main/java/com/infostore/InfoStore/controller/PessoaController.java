package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/")
    public List<Pessoa> buscarTodos() {
        return pessoaService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Pessoa inserir(@RequestBody Pessoa pessoa) {
        return pessoaService.inserir(pessoa);
    }

    @PutMapping(value = "/")
    public Pessoa alterar(@RequestBody Pessoa pessoa) {
        return pessoaService.alterar(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        pessoaService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
