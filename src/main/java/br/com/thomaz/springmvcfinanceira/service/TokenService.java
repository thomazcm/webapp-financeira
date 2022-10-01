package br.com.thomaz.springmvcfinanceira.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import br.com.thomaz.springmvcfinanceira.config.exception.ErroDeSessaoException;
import br.com.thomaz.springmvcfinanceira.controller.dto.UsuarioDto;
import br.com.thomaz.springmvcfinanceira.model.Usuario;
import br.com.thomaz.springmvcfinanceira.repository.UsuarioRepository;

@Service
public class TokenService {
    
    @Autowired private UsuarioRepository repository;
    @Autowired private ApiService http;

    public String getToken() {
        Usuario usuario = buscarUsuarioLogado();
        String token = usuario.tokenExpirado() ? refreshToken(usuario) : usuario.getToken();
        return token;
    }

    private String refreshToken(Usuario usuario) {
        String responseBody = http.doRequest(HttpMethod.POST, "/auth", new UsuarioDto(usuario)).body();
        String token =  responseBody.substring(10, responseBody.indexOf("\",\""));
        usuario.setToken(token);
        repository.save(usuario);
        return token;
    }

    private Usuario buscarUsuarioLogado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            Usuario usuario = repository.findByEmail(email).get();
            return usuario;
        } catch(NoSuchElementException e) {
            throw new ErroDeSessaoException();
        }
    }

    
}
