package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.service.PessoaGerenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infostore/pessoa-gerenciamento")
public class PessoaGerenciamentoController {

    @Autowired
    private PessoaGerenciamentoService pessoaGerenciamentoService;

    @PostMapping(value = "/")
    public String recuperarCodigo(@RequestParam("email") String email) {
        return pessoaGerenciamentoService.solicitarCodigo(email);
    }
}
