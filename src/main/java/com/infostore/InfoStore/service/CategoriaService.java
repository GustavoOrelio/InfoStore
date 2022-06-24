package com.infostore.InfoStore.service;

import com.infostore.InfoStore.domain.Categoria;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    private boolean existsById(Long id){
        return categoriaRepository.existsById(id);
    }

    public Categoria findById(Long id) throws ResourceNotFoundException {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if(categoria == null) {
            throw new ResourceNotFoundException("Categoria não encontrada com o id: " + id);
        }
        else return categoria;
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }

    public Page<Categoria> findAllByDescricao(String descricao, Pageable page) {
        Page<Categoria> categoria = categoriaRepository.findByDescricao(descricao, page);
        return categoria;
    }

    public Categoria save(Categoria categoria) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(categoria.getDescricao())) {
            if (categoria.getIdCategoria() != null && existsById(categoria.getIdCategoria())) {
                throw new ResourceAlreadyExistsException("Categoria com id: " + categoria.getIdCategoria() + " já existe");
            }
            return categoriaRepository.save(categoria);
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar a categoria");
            exc.addErrorMessage("Categoria está vazia ou é nula");
            throw exc;
        }
    }

    public void update(Categoria categoria) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(categoria.getDescricao())){
            if (!existsById(categoria.getIdCategoria())){
                throw  new ResourceNotFoundException("Categoria não encontrada com o id: " + categoria.getIdCategoria());
            }
            categoriaRepository.save(categoria);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar a categoria");
            exc.addErrorMessage("Categoria está nula ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Categoria não encontrada com o id: " + id);
        }
        else{
            categoriaRepository.deleteById(id);
        }
    }

    public Long count() {
        return categoriaRepository.count();
    }
}
