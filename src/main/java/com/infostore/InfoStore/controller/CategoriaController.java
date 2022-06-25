package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.domain.Categoria;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/infostore")
public class CategoriaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Busca categoria", description = "Busca todos os categorias", tags = {"categoria"})
    @GetMapping(value = "/categoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Categoria>> findAll(
            @Parameter(description = "Insira a descrição da categoria", allowEmptyValue = true )
            @RequestBody(required=false) String descricao,
            @Parameter(description = "Paginação", example = "{\"page\":0,\"size\":10}", allowEmptyValue = true)
            Pageable pageable){
        System.out.println(descricao);
        if (StringUtils.isEmpty(descricao)) {
            return ResponseEntity.ok(categoriaService.findAll(pageable));
        }
        else {
            return ResponseEntity.ok(categoriaService.findAllByDescricao(descricao, pageable));
        }
    }

    @Operation(summary = "Busca ID", description = "Busca um categoria por ID", tags = {"categoria"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrado"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrado"),
    })
    @GetMapping(value = "/categoria/{id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable long id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @Operation(summary = "Cadastrar categoria", description = "Cadastrar um categoria", tags = {"categoria"})
    @PostMapping(value = "/categoria")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria)
            throws URISyntaxException {
        try {
            Categoria novoCategoria = categoriaService.save(categoria);
            return ResponseEntity.created(new URI("/infostore/categoria/" + novoCategoria.getId())).body(categoria);
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Alterar categoria ID", description = "Altera um categoria inserindo o ID do mesmo", tags = {"categoria"})
    @PutMapping(value = "/categoria/{id}")
    public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria, @PathVariable long id) {
        try {
            categoria.setId(id);
            categoriaService.update(categoria);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Deletar por ID", description = "Delete uma categoria pelo ID", tags = {"categoria"})
    @DeleteMapping(path="/categoria/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable long id){
        try {
            categoriaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
