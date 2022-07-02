package com.infostore.InfoStore.service;

import com.infostore.InfoStore.domain.Produto;
import com.infostore.InfoStore.domain.ProdutoPreco;
import com.infostore.InfoStore.domain.Usuario;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.repository.ProdutoPrecoRepository;
import com.infostore.InfoStore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoPrecoRepository produtoPrecoRepository;

    private boolean existsById(Long id){
        return produtoRepository.existsById(id);
    }

    public Produto findById(Long id) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if(produto == null) {
            throw new ResourceNotFoundException("Produto não encontrado com o id: " + id);
        }
        else return produto;
    }

    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> findAllByDescricao(String descricao, Pageable page) {
        Page<Produto> produto = produtoRepository.findByDescricao(descricao, page);
        return produto;
    }

    public Page<Produto> findAllByMarca(String descricao, Pageable page) {
        Page<Produto> produto = produtoRepository.findByMarca(descricao, page);
        return produto;
    }

    public Page<Produto> findAllByMarcaId(Long id, Pageable page) {
        Page<Produto> produto = produtoRepository.findByMarcaId(id, page);
        return produto;
    }

    public Produto save(Produto produto) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(produto.getDescricao())) {
            if (produto.getId() != null && existsById(produto.getId())) {
                throw new ResourceAlreadyExistsException("Produto com id: " + produto.getId() + " já existe");
            }
            Produto produtoNovo = produtoRepository.save(produto);
            
            ProdutoPreco produtoPreco = new ProdutoPreco();
            produtoPreco.setProduto(produtoNovo);
            produtoPreco.setValorCusto(produtoNovo.getValorCusto());
            produtoPreco.setValorVenda(produtoNovo.getValorVenda());
            produtoPrecoRepository.save(produtoPreco);

            return produtoNovo;
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar o produto");
            exc.addErrorMessage("Produto está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Produto produto) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(produto.getDescricao())){
            if (!existsById(produto.getId())){
                throw  new ResourceNotFoundException("Produto não encontrado com o id: " + produto.getId());
            }
            if(produto.getValorVenda() != produtoRepository.buscarPorId(produto.getId()).getValorVenda() || produto.getValorCusto() != produtoRepository.buscarPorId(produto.getId()).getValorCusto()) {

                ProdutoPreco produtoPreco = new ProdutoPreco();
                produtoPreco.setProduto(produto);
                produtoPreco.setValorCusto(produto.getValorCusto());
                produtoPreco.setValorVenda(produto.getValorVenda());
                produtoPrecoRepository.save(produtoPreco);
            }


        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar o produto");
            exc.addErrorMessage("Produto está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Produto não encontrado com o id: " + id);
        }
        else{
            produtoRepository.deleteById(id);
        }
    }

    public Long count() {
        return produtoRepository.count();
    }
}
