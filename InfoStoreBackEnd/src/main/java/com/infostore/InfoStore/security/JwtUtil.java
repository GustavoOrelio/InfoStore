package com.infostore.InfoStore.security;

import com.infostore.InfoStore.model.Pessoa;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUtil {

    private String chaveSecreta = "chaveSecretaParaGerarToken";
    private int validadeToken = 900000;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public String gerarTokenUsername(Pessoa pessoa) {
        return Jwts.builder().setSubject(pessoa.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() + validadeToken)).
                signWith(SignatureAlgorithm.HS512, chaveSecreta).compact();
    }

    public String getEmailToken(String token) {
        return Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token, HttpServletRequest request) {
        try {
            Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura Inválida", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado", e.getMessage());
            request.setAttribute("validacaoToken", "Token expirado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token não suportado", e.getMessage());

        }
        return false;
    }
}
