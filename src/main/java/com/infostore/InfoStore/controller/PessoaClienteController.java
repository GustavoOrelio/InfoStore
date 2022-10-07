package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.dto.PessoaClienteRequestDTO;
import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infostore/cliente")
public class PessoaClienteController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping(value = "/")
    public Pessoa inserir(@RequestBody PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
        return pessoaService.inserir(pessoa);
    }
}
