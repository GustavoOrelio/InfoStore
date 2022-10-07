package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.PermissaoPessoa;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.repository.PermissaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class PermissaoUsuarioService {
	
	@Autowired
	private PermissaoUsuarioRepository permissaoUsuarioRepository;
	
	private boolean existsbyId(Long id) {
		return permissaoUsuarioRepository.existsById(id);
	}
	
	public PermissaoPessoa findById(Long id) throws ResourceNotFoundException {
		PermissaoPessoa permissaoUsuario = permissaoUsuarioRepository.findById(id).orElse(null);
		if(permissaoUsuario == null) {
			throw new ResourceNotFoundException("Produto não foi encontrado com o id: "+id);
		}else {
			return permissaoUsuario;
		}
	}
	
	public Page<PermissaoPessoa> findAll(Pageable pageable){
		return permissaoUsuarioRepository.findAll(pageable);
	}
	
	public PermissaoPessoa save(PermissaoPessoa permissaoUsuario) throws BadResourceException, ResourceAlreadyExistsException {
		if(permissaoUsuario.getId() != null) {
			if(existsbyId(permissaoUsuario.getId())) {
				throw new ResourceAlreadyExistsException("Permissao Usuario com o id: "+permissaoUsuario.getId()+"\n já existe");
			}
			return permissaoUsuarioRepository.save(permissaoUsuario);			
		}else {
			BadResourceException exc = new BadResourceException("Erro ao salvar Permissao Usuario");
			exc.addErrorMessage("Permissao Usuario está vazio ou é nulo");
			throw exc;
		}
	}
	
	public void update(PermissaoPessoa permissaoUsuario) throws BadResourceException, ResourceNotFoundException{
		if(permissaoUsuario.getId() != null) {
			if(!existsbyId(permissaoUsuario.getId())) {
				throw new ResourceNotFoundException("Produto não encontrado com o id: "+permissaoUsuario.getId());
			}
			permissaoUsuarioRepository.save(permissaoUsuario);
		}
	}
	
	public void deleteById(Long id) throws ResourceNotFoundException{
		if(!existsbyId(id)) {
			throw new ResourceNotFoundException("Produto não encontrado com o id: "+id);
		}else {
			permissaoUsuarioRepository.deleteById(id);
		}
	}
	
	public Long count() {
		return permissaoUsuarioRepository.count();
	}
}
