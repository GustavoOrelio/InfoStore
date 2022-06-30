package com.infostore.InfoStore.service;

import com.infostore.InfoStore.domain.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoService {

    public Endereco findEnderecoByCep(String cep) {
        String url = "https://brasilapi.com.br/api/cep/v2/"+cep;
        RestTemplate rest = new RestTemplate();
        Endereco endereco = rest.getForObject(url, Endereco.class);
        return endereco;
    }
}