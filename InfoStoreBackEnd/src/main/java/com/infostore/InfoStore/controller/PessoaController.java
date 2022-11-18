package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/pessoa")
@CrossOrigin
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Pessoa> buscarTodos(){
        return pessoaService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Pessoa inserir(@RequestBody Pessoa objeto){
        return pessoaService.inserir(objeto);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Pessoa alterar(@RequestBody Pessoa objeto){
        return pessoaService.alterar(objeto);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        pessoaService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
