package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.service.PessoaGerenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infostore/pessoa-gerenciamento")
public class PessoaGerenciamentoController {

    @Autowired
    private PessoaGerenciamentoService pessoaGerenciamentoService;

    @PostMapping(value = "/senha-codigo")
    public String recuperarCodigo(@RequestBody Pessoa pessoa) {
        return pessoaGerenciamentoService.solicitarCodigo(pessoa.getEmail());
    }

    @PostMapping(value = "/senha-alterar")
    public String alterarSenha(@RequestBody Pessoa pessoa) {
        return pessoaGerenciamentoService.alterarSenha(pessoa);
    }
}
