package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.ProdutoImagens;
import com.infostore.InfoStore.service.ProdutoImagensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/infostore/produtoImagens")
public class ProdutoImagensController {

    @Autowired
    private ProdutoImagensService produtoImagensService;

    @GetMapping(value = "/")
    public List<ProdutoImagens> buscarTodos() {
        return produtoImagensService.buscarTodos();
    }

    @PostMapping(value = "/")
    public ProdutoImagens inserir(@RequestParam("idProduto") Long idProduto, @RequestParam("file") MultipartFile file) {
        return produtoImagensService.inserir(idProduto, file);
    }

    @PutMapping(value = "/")
    public ProdutoImagens alterar(@RequestBody ProdutoImagens produtoImagens) {
        return produtoImagensService.alterar(produtoImagens);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        produtoImagensService.excuir(id);
        return ResponseEntity.ok().build();
    }
}
