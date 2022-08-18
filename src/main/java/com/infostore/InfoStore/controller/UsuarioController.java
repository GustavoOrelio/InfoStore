package com.infostore.InfoStore.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import com.infostore.InfoStore.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.infostore.InfoStore.model.Usuario;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/infostore")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Busca usuario", description = "Busca todos os usuarios", tags = {"usuario"})
    @GetMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UsuarioDTO>> findAll(
            @Parameter(description = "Insira o nome do usuario", allowEmptyValue = true )
            @RequestBody(required=false) String nome,
            @Parameter(description = "Paginação", example = "{\"page\":0,\"size\":10}", allowEmptyValue = true)
            Pageable pageable){
        System.out.println(nome);
        if (StringUtils.isEmpty(nome)) {
            //return ResponseEntity.ok(usuarioService.findAll(pageable));
            return ResponseEntity.ok(new UsuarioDTO().converterListaUsuarioDTO(usuarioService.findAll(pageable)));
        }
        else {
           //return null;
            return ResponseEntity.ok(new UsuarioDTO().converterListaUsuarioDTO(usuarioService.findAllByNome(nome, pageable)));
        }
    }

    @Operation(summary = "Busca ID", description = "Busca um usuario por ID", tags = {"usuario"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado"),
    })
    @GetMapping(value = "/usuario/{id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findUsuarioById(@PathVariable long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @Operation(summary = "Cadastrar usuario", description = "Cadastrar um usuario", tags = {"usuario"})
    @PostMapping(value = "/usuario")
    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody Usuario usuario)
            throws URISyntaxException {
        try {
            Usuario novoUsuario = usuarioService.save(usuario);

            return ResponseEntity.created(new URI("/infostore/usuario/" + novoUsuario.getId()))
                    .body(new UsuarioDTO().converter(usuario));
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Alterar usuario ID", description = "Altera um usuario inserindo o ID do mesmo", tags = {"usuario"})
    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario, @PathVariable long id) {
        try {
            usuario.setId(id);
            usuarioService.update(usuario);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Deletar por ID", description = "Delete um usuario pelo ID", tags = {"usuario"})
    @DeleteMapping(path="/usuario/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable long id){
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

}
