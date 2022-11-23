package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.dto.PessoaClienteRequestDTO;
import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.service.PessoaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/infostore/cliente")
public class PessoaClienteController {

    @Autowired
    private PessoaClienteService pessoaService;

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Pessoa inserir(@RequestBody PessoaClienteRequestDTO pessoaClienteRequestDTO) {

        return pessoaService.registrar(pessoaClienteRequestDTO);
    }
}
