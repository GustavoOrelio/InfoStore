package com.infostore.InfoStore.service;

import com.infostore.InfoStore.domain.Usuario;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }

    public Usuario findById(Long id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) {
            throw new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
        }
        else return usuario;
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> findAllByNome(String nome, Pageable page) {
        Page<Usuario> usuario = usuarioRepository.findByNome(nome, page);
        return usuario;
    }

    public Usuario save(Usuario usuario) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(usuario.getNome())) {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            if (usuario.getId() != null && existsById(usuario.getId())) {
                throw new ResourceAlreadyExistsException("Usuario com id: " + usuario.getId() + " já existe");
            }
            return usuarioRepository.save(usuario);
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar o usuario");
            exc.addErrorMessage("Usuario está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Usuario usuario) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(usuario.getNome())){
            if (!existsById(usuario.getId())){
                throw  new ResourceNotFoundException("Usuario não encontrado com o id: " + usuario.getId());
            }
            usuarioRepository.save(usuario);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar o usuario");
            exc.addErrorMessage("Usuario está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
        }
        else{
            usuarioRepository.deleteById(id);
        }
    }

    public Long count() {
        return usuarioRepository.count();
    }
}
