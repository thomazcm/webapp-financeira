package br.com.thomaz.springmvcfinanceira.config.exception;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ErroDeSessaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ErroDeSessaoException() {
        super("erro ao buscar dados do usuário logado");
    }
}
