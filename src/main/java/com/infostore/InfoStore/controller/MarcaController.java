package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.domain.Marca;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.service.MarcaService;
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
public class MarcaController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MarcaService marcaService;

    @Operation(summary = "Busca marca", description = "Busca todos os marcas", tags = {"marca"})
    @GetMapping(value = "/marca", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Marca>> findAll(
            @Parameter(description = "Insira a descrição da marca", allowEmptyValue = true )
            @RequestBody(required=false) String descricao,
            @Parameter(description = "Paginação", example = "{\"page\":0,\"size\":10}", allowEmptyValue = true)
            Pageable pageable){
        System.out.println(descricao);
        if (StringUtils.isEmpty(descricao)) {
            return ResponseEntity.ok(marcaService.findAll(pageable));
        }
        else {
            return ResponseEntity.ok(marcaService.findAllByDescricao(descricao, pageable));
        }
    }

    @Operation(summary = "Busca ID", description = "Busca um marca por ID", tags = {"marca"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrado"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrado"),
    })
    @GetMapping(value = "/marca/{id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Marca> findMarcaById(@PathVariable long id) {
        try {
            Marca marca = marcaService.findById(id);
            return ResponseEntity.ok(marca);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @Operation(summary = "Cadastrar marca", description = "Cadastrar um marca", tags = {"marca"})
    @PostMapping(value = "/marca")
    public ResponseEntity<Marca> addMarca(@RequestBody Marca marca)
            throws URISyntaxException {
        try {
            Marca novoMarca = marcaService.save(marca);
            return ResponseEntity.created(new URI("/infostore/marca/" + novoMarca.getIdMarca())).body(marca);
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Alterar marca ID", description = "Altera um marca inserindo o ID do mesmo", tags = {"marca"})
    @PutMapping(value = "/marca/{id}")
    public ResponseEntity<Marca> updateMarca(@Valid @RequestBody Marca marca, @PathVariable long id) {
        try {
            marca.setIdMarca(id);
            marcaService.update(marca);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Deletar por ID", description = "Delete uma marca pelo ID", tags = {"marca"})
    @DeleteMapping(path="/marca/{id}")
    public ResponseEntity<Void> deleteMarcaById(@PathVariable long id){
        try {
            marcaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
