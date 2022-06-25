package com.infostore.InfoStore.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import com.infostore.InfoStore.domain.Produto;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.service.ProdutoService;
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
public class ProdutoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Busca produto", description = "Busca todos os produtos", tags = {"produto"})
    @GetMapping(value = "/produto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Produto>> findAll(
            @Parameter(description = "Insira a descriçãp do produto", allowEmptyValue = true )
            @RequestBody(required=false) String descricao,
            @Parameter(description = "Paginação", example = "{\"page\":0,\"size\":10}", allowEmptyValue = true)
            Pageable pageable){
        if (StringUtils.isEmpty(descricao)) {
            return ResponseEntity.ok(produtoService.findAll(pageable));
        }
        else {
            return ResponseEntity.ok(produtoService.findAllByDescricao(descricao, pageable));
        }
    }

    @GetMapping(value = "/produto/marca/{descricao}")
    public ResponseEntity<Page<Produto>> findByMarcaDescricao(String descricao, Pageable pageable ){

        return ResponseEntity.ok(produtoService.findAllByMarca(descricao, pageable));
    }

    @GetMapping(value = "/produto/marca/{id}")
    public ResponseEntity<Page<Produto>> findByIdMarca(Long id, Pageable pageable ){
        return ResponseEntity.ok(produtoService.findAllByMarcaId(id, pageable));
    }

    @Operation(summary = "Busca ID", description = "Busca um produto por ID", tags = {"produto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    @GetMapping(value = "/produto/{id}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> findProdutoById(@PathVariable long id) {
        try {
            Produto produto = produtoService.findById(id);
            return ResponseEntity.ok(produto);
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @Operation(summary = "Cadastrar produto", description = "Cadastrar um produto", tags = {"produto"})
    @PostMapping(value = "/produto")
    public ResponseEntity<Produto> addProduto(@RequestBody Produto produto)
            throws URISyntaxException {
        try {
            Produto novoProduto = produtoService.save(produto);
            return ResponseEntity.created(new URI("/infostore/produto/" + novoProduto.getId())).body(produto);
        } catch (ResourceAlreadyExistsException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Alterar produto ID", description = "Altera um produto inserindo o ID do mesmo", tags = {"produto"})
    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto, @PathVariable long id) {
        try {
            produto.setId(id);
            produtoService.update(produto);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Deletar por ID", description = "Delete um produto pelo ID", tags = {"produto"})
    @DeleteMapping(path="/produto/{id}")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable long id){
        try {
            produtoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

}
