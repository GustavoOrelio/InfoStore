package com.infostore.InfoStore.service;

import com.infostore.InfoStore.model.Produto;
import com.infostore.InfoStore.model.ProdutoImagens;
import com.infostore.InfoStore.repository.ProdutoImagensRepository;
import com.infostore.InfoStore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoImagensService {

    @Autowired
    private ProdutoImagensRepository produtoImagensRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoImagens> buscarTodos() {
        return produtoImagensRepository.findAll();
    }

    public ProdutoImagens inserir(Long idProduto, MultipartFile file) {
        Produto produto = produtoRepository.findById(idProduto).get();
        ProdutoImagens produtoImagens = new ProdutoImagens();

        try {
            if (!file.isEmpty()){
                byte[] bytes = file.getBytes();
                String nomeImagem = String.valueOf(produto.getId()) + file.getOriginalFilename();
                Path caminho = Paths.get("c:/imagens/" + nomeImagem);
                Files.write(caminho, bytes);
                produtoImagens.setNome(nomeImagem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        produtoImagens.setProduto(produto);
        produtoImagens.setDataCriacao(new Date());
        produtoImagens = produtoImagensRepository.saveAndFlush(produtoImagens);
        return produtoImagens;
    }

    public ProdutoImagens alterar(ProdutoImagens produtoImagens) {
        produtoImagens.setDataCriacao(new Date());
        return produtoImagensRepository.saveAndFlush(produtoImagens);
    }

    public void excuir(Long id) {
        ProdutoImagens produtoImagens = produtoImagensRepository.findById(id).get();
        produtoImagensRepository.delete(produtoImagens);
    }
}
