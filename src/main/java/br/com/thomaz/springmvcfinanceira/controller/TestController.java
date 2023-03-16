package br.com.thomaz.springmvcfinanceira.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.thomaz.springmvcfinanceira.model.Usuario;
import br.com.thomaz.springmvcfinanceira.repository.UsuarioRepository;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> listar() {

        return repository.findByDataCriacaoLessThanAndEhUsuarioDemoEquals
                (LocalDate.now().minusDays(2), false);
    }

}
