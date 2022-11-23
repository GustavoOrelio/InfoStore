package com.infostore.InfoStore.controller;

import com.infostore.InfoStore.model.Pessoa;
import com.infostore.InfoStore.security.JwtUtil;
import com.infostore.InfoStore.service.PessoaGerenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/infostore/pessoa-gerenciamento")
public class PessoaGerenciamentoController {

    @Autowired
    private PessoaGerenciamentoService pessoaGerenciamentoService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/senha-codigo")
    @CrossOrigin("http://localhost:3000")
    public String recuperarCodigo(@RequestBody Pessoa pessoa) {
        return pessoaGerenciamentoService.solicitarCodigo(pessoa.getEmail());
    }

    @PostMapping("/senha-alterar")
    @CrossOrigin("http://localhost:3000")
    public String alterarSenha(@RequestBody Pessoa pessoa) {
        return pessoaGerenciamentoService.alterarSenha(pessoa);
    }

    @PostMapping("/login")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> login(@RequestBody Pessoa pessoa) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(pessoa.getEmail(), pessoa.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Pessoa autenticado = (Pessoa) authentication.getPrincipal();
        String token = jwtUtil.gerarTokenUsername(autenticado);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("permissoes", autenticado.getAuthorities());
        return ResponseEntity.ok(map);
    }
}