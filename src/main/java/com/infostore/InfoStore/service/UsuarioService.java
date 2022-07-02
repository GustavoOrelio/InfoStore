package com.infostore.InfoStore.service;

import com.infostore.InfoStore.domain.Permissao;
import com.infostore.InfoStore.domain.PermissaoUsuario;
import com.infostore.InfoStore.domain.Usuario;
import com.infostore.InfoStore.exception.BadResourceException;
import com.infostore.InfoStore.exception.ResourceAlreadyExistsException;
import com.infostore.InfoStore.exception.ResourceNotFoundException;
import com.infostore.InfoStore.repository.PermissaoRepository;
import com.infostore.InfoStore.repository.PermissaoUsuarioRepository;
import com.infostore.InfoStore.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioService {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PermissaoRepository permissaoRepository;
    @Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;

    private boolean existsById(Long id){
        return usuarioRepository.existsById(id);
    }

    public Usuario findById(Long id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) {
            throw new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
        }
        else return usuario;
    }

    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> findAllByNome(String nome, Pageable page) {
        Page<Usuario> usuario = usuarioRepository.findByNome(nome, page);
        return usuario;
    }

    public Usuario save(Usuario usuario) throws BadResourceException, ResourceAlreadyExistsException {
        if(!StringUtils.isEmpty(usuario.getNome()) && validarCPF(usuario.getCpf())) {
            Matcher matcher  = pattern.matcher(usuario.getEmail());
            if(matcher.matches()==false) usuario.setEmail("");
            usuario.setImagemPerfil(Base64.getEncoder().encode(usuario.getImagemPerfil()));;
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            if (usuario.getId() != null && existsById(usuario.getId())) {
                throw new ResourceAlreadyExistsException("Usuario com id: " + usuario.getId() + " já existe");
            }
            List<Permissao> permissoes = permissaoRepository.buscarPermissaoNome("funcionario");
            Usuario usuarioNovo = usuarioRepository.save(usuario);
            if(permissoes.size()>0){
                PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
                permissaoUsuario.setPermissao(permissoes.get(0));
                permissaoUsuario.setUsuario(usuarioNovo);
                permissaoUsuarioRepository.save(permissaoUsuario);
            }
            return usuarioRepository.save(usuario);
        }
        else {
            BadResourceException exc = new BadResourceException("Erro ao salvar o usuario");
            exc.addErrorMessage("Usuario está vazio ou é nulo");
            throw exc;
        }
    }

    public void update(Usuario usuario) throws BadResourceException, ResourceNotFoundException {
        if(!StringUtils.isEmpty(usuario.getNome()) && validarCPF(usuario.getCpf())) {
            Matcher matcher  = pattern.matcher(usuario.getEmail());
            if(matcher.matches()==false) usuario.setEmail("");
            usuario.setImagemPerfil(Base64.getEncoder().encode(usuario.getImagemPerfil()));;
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            if (!existsById(usuario.getId())){
                throw  new ResourceNotFoundException("Usuario não encontrado com o id: " + usuario.getId());
            }
            usuarioRepository.save(usuario);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar o usuario");
            exc.addErrorMessage("Usuario está nulo ou em branco");
            throw exc;
        }
    }

    public void deleteById(Long id) throws  ResourceNotFoundException {
        if (!existsById(id)) {
            throw  new ResourceNotFoundException("Usuario não encontrado com o id: " + id);
        }
        else{
            usuarioRepository.deleteById(id);
        }
    }

    public Long count() {
        return usuarioRepository.count();
    }

    public static boolean validarCPF(String CPF) {

        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (java.util.InputMismatchException erro) {
            return(false);
        }
    }

}
