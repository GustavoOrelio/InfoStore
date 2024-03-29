package com.infostore.InfoStore.service;

import com.infostore.InfoStore.dto.PessoaClienteRequestDTO;
import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.repository.PessoaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
        Pessoa objetoNovo = pessoaRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(objetoNovo);
        //emailService.enviarEmailTexto(objetoNovo.getEmail(), "Cadastro na Loja Tabajara", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        Map<String, Object> proprMap = new HashMap<>();
        proprMap.put("nome", objetoNovo.getNome());
        proprMap.put("mensagem", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        emailService.enviarEmailTemplate(objetoNovo.getEmail(), "Cadastro na Loja Tabajara", proprMap);
        return objetoNovo;
    }


}