package com.infostore.InfoStore.dto;

import com.infostore.InfoStore.domain.Usuario;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.Date;

@Data
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String email;
    private Date dataCadastro;

    public UsuarioDTO converter(Usuario usuario){
        BeanUtils.copyProperties(usuario, this);
        return this;
    }

    public Page<UsuarioDTO> converterListaUsuarioDTO(Page<Usuario> pageUsuario){
        Page<UsuarioDTO> listaDTO = pageUsuario.map(this::converter);
        return listaDTO;
    }
}
