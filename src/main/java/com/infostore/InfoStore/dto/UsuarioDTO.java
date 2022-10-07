package com.infostore.InfoStore.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

//timestamp
@Data
public class UsuarioDTO {
    private String nome;
    private String cpf;
    private String email;

    public UsuarioDTO converter(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuario, usuarioDTO);
        return usuarioDTO;
    }

    public Page<UsuarioDTO> converterListaUsuarioDTO(Page<Usuario> pageUsuario){
        System.out.println(pageUsuario.getContent().size());
        Page<UsuarioDTO> listaDTO = pageUsuario.map(this::converter);
        return listaDTO;
    }
}
