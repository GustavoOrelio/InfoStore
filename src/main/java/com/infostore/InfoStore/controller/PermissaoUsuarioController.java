package com.infostore.InfoStore.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import com.infostore.InfoStore.model.PermissaoUsuario;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.service.PermissaoUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/infostore")
@Tag(name="permissaoUsuario", description="Api De Permissão do Usuário")
public class PermissaoUsuarioController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PermissaoUsuarioService permissaoUsuarioService;

	@GetMapping(value="/permissaoUsuario")
	public ResponseEntity<Page<PermissaoUsuario>> findAll(Pageable pageable){
		return ResponseEntity.ok(permissaoUsuarioService.findAll(pageable));
	}

	@PostMapping(value="/permissaoUsuario")
	public ResponseEntity<PermissaoUsuario> addProduto(@RequestBody PermissaoUsuario permissaoUsuario) throws URISyntaxException{
		try {
			PermissaoUsuario p = permissaoUsuarioService.save(permissaoUsuario);
			return ResponseEntity.created(new URI("/api/produto/"+p.getId())).body(permissaoUsuario);
		}catch(ResourceAlreadyExistsException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping(value="/permissaoUsuario/{id}")
	public ResponseEntity<PermissaoUsuario> updateProduto(@Valid @RequestBody PermissaoUsuario permissaoUsuario, @PathVariable long id) throws BadResourceException{
		try {
			permissaoUsuario.setId(id);
			permissaoUsuarioService.update(permissaoUsuario);
			return ResponseEntity.ok().build();
		}catch(ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.notFound().build();
		}catch(BadResourceException ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping(path="/permissaoUsuario/{id}")
	public ResponseEntity<PermissaoUsuario> deleteProdutoById(@PathVariable long id){
		try {
			permissaoUsuarioService.deleteById(id);
			return ResponseEntity.ok().build();
		}catch(ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		}
	}
}
