package com.infostore.InfoStore.service;

import com.infostore.InfoStore.dto.PessoaClienteRequestDTO;
import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.repository.PermissaoRepository;
import com.infostore.InfoStore.repository.PessoaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PessoaClienteService {
    @Autowired
    private PessoaClienteRepository pessoaRepository;
    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;

    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
        pessoa.setDataCriacao(new Date());
        Pessoa pessoaNovo = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(pessoaNovo);
        emailService.enviarEmailTexto(pessoaNovo.getEmail(), "Cadastro na Loja Tabajara", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!!");
        return pessoaNovo;
    }

}
