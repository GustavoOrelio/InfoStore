package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Permissao;
import com.infostore.InfoStore.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infostore/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoService permissaoService;

	@GetMapping(value = "/")
	public List<Permissao> buscarTodos() {
		return permissaoService.buscarTodos();
	}

	@PostMapping(value = "/")
	public Permissao inserir(@RequestBody Permissao permissao) {
		return permissaoService.inserir(permissao);
	}

	@PutMapping(value = "/")
	public Permissao alterar(@RequestBody Permissao permissao) {
		return permissaoService.alterar(permissao);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		permissaoService.excuir(id);
		return ResponseEntity.ok().build();
	}
}
