package br.com.thomaz.springmvcfinanceira.controller;

import java.util.Random;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.thomaz.springmvcfinanceira.config.exception.EmailJaExisteException;
import br.com.thomaz.springmvcfinanceira.controller.dto.UsuarioDto;
import br.com.thomaz.springmvcfinanceira.controller.form.UsuarioForm;
import br.com.thomaz.springmvcfinanceira.repository.UsuarioRepository;
import br.com.thomaz.springmvcfinanceira.service.ApiService;
import br.com.thomaz.springmvcfinanceira.service.TokenService;

@Controller
@RequestMapping(("/login"))
public class LoginController {

    @Autowired private UsuarioRepository repository;
    @Autowired private BCryptPasswordEncoder encoder;
    @Autowired private ApiService http;
    @Autowired private TokenService tokenService;

    @GetMapping
    public String loginPage(Model model, 
            @RequestParam(required = false) Boolean cadastrado,
            @RequestParam(required = false) Boolean loginFail,
            @RequestParam(required = false) Boolean erroToken,
            @RequestParam(required = false) String usuarioCadastrado) {
        
        model.addAttribute("cadastrado", cadastrado);
        model.addAttribute("loginFail", loginFail);
        model.addAttribute("erroToken", erroToken);
        model.addAttribute("usuarioCadastrado", usuarioCadastrado);
        
        if (cadastrado == null && usuarioCadastrado.length() > 1) {
            model.addAttribute("senha", "123456");
        }
        
        return "login";
    }
    
    @GetMapping("/redirect")
    public String loginRedirect(Model model) {
        model.addAttribute("token", tokenService.getToken());
        return "loginRedirect";
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model, UsuarioForm usuarioForm) {
        return "cadastro";
    }
    
    @GetMapping("/demo")
    public String cadastroDemo() {
        UsuarioForm demoForm = new UsuarioForm();
        
        demoForm.setEmail("usuarioDemo" + new Random().nextInt(1, Integer.MAX_VALUE)
                + "@mail.com");
        while (repository.existsById(demoForm.getEmail())) {
            demoForm.setEmail("usuarioDemo" + new Random().nextInt(1, Integer.MAX_VALUE)
                    + "@mail.com");
        }
        demoForm.setNome("demo");
        demoForm.setSenha("123456");
        
        var usuarioDemo = demoForm.toUser(encoder);
        repository.save(usuarioDemo);
        
        http.doRequest(HttpMethod.POST, "/usuarios/demo", new UsuarioDto(usuarioDemo));
        
        return "redirect:/login?usuarioCadastrado=" + demoForm.getEmail();
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(Model model, @Valid UsuarioForm usuarioForm, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro";
        }
        if (repository.existsById(usuarioForm.getEmail())) {
            throw new EmailJaExisteException();
        }
        
        var usuario = usuarioForm.toUser(encoder);
        var usuarioDto = new UsuarioDto(usuario);

        var response = http.doRequest(HttpMethod.POST, "/usuarios", usuarioDto);
        if (response.statusCode() == 201) {
            repository.save(usuario);
            return "redirect:/login?cadastrado=true&&usuarioCadastrado=" + usuarioForm.getEmail();
        }
        return "cadastro";
    }

}
