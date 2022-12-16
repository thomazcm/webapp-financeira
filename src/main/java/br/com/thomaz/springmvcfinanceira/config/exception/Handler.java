package br.com.thomaz.springmvcfinanceira.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.com.thomaz.springmvcfinanceira.controller.form.UsuarioForm;

@ControllerAdvice
public class Handler {
    
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailJaExisteException.class)
    public String handle(EmailJaExisteException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        model.addAttribute("usuarioForm", new UsuarioForm());
        return "/cadastro";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErroDeSessaoException.class)
    public String handle(ErroDeSessaoException e) {
        return "redirect:/logout";
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErroAoGerarTokenDeAcessoException.class)
    public String handle(ErroAoGerarTokenDeAcessoException e, Model model) {
        return "redirect:/login?erroToken=true";
    }
}
