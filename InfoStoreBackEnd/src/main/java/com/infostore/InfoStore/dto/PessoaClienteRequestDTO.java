package com.infostore.InfoStore.dto;

import com.infostore.InfoStore.model.Cidade;
import com.infostore.InfoStore.model.Pessoa;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PessoaClienteRequestDTO {

    private String nome;
    private String cpf;
    private String email;
    private String endereco;
    private String cep;
    private Cidade cidade;

    public Pessoa converter(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaClienteRequestDTO, pessoa);
        return pessoa;
    }
}
