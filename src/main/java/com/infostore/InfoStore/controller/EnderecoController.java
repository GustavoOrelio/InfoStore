package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.domain.Endereco;
import com.infostore.InfoStore.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infostore")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(value="/cep/{cep}")
    public Endereco findEnderecoByCep(@PathVariable String cep) {
        Endereco endereco = enderecoService.findEnderecoByCep(cep);
        return endereco;
    }

}
