package br.com.thomaz.springmvcfinanceira.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.com.thomaz.springmvcfinanceira.controller.form.UsuarioForm;

@ControllerAdvice
public class ViewExceptionHandler {
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailJaExisteException.class)
    public String handle(EmailJaExisteException e, Model model) {
        model.addAttribute("exception", e.getMessage());
        model.addAttribute("usuarioForm", new UsuarioForm());
        return "/login/cadastro";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErroDeSessaoException.class)
    public String handle(ErroDeSessaoException e, Model model) {
        return "redirect:/logout";
    }
}
