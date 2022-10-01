package br.com.thomaz.springmvcfinanceira.config.exception;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailJaExisteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailJaExisteException() {
        super("não foi possível concluir o cadastro, este endereço de email já está cadastrado");
    }
}
