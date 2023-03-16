package br.com.thomaz.springmvcfinanceira.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.thomaz.springmvcfinanceira.controller.form.UsuarioForm;
import br.com.thomaz.springmvcfinanceira.model.Usuario;
import br.com.thomaz.springmvcfinanceira.repository.UsuarioRepository;

@Service
public class DemoService {

    public Usuario criarUsuarioDemo(UsuarioRepository repository, BCryptPasswordEncoder encoder) {
        UsuarioForm demoForm = new UsuarioForm();
        demoForm.setEmail("usuarioDemo" + new Random().nextInt(1, Integer.MAX_VALUE) + "@mail.com");
        while (repository.existsById(demoForm.getEmail())) {
            demoForm.setEmail("usuarioDemo" + new Random().nextInt(1, Integer.MAX_VALUE) + "@mail.com");
        }
        demoForm.setNome("demo");
        demoForm.setSenha("123456");
        var usuarioDemo = demoForm.toUser(encoder);
        usuarioDemo.ehDemo();
        return usuarioDemo;
    }

    public void limparUsuariosDemo(UsuarioRepository repository, ApiService http) {
        List<Usuario> demosVelhos = repository.findByDataCriacaoLessThanAndEhUsuarioDemoEquals
                (LocalDate.now().minusDays(2), true);
        demosVelhos.forEach(d -> {
            var response = http.doRequest(HttpMethod.DELETE, "/usuarios/" + d.getUsername());
            if (response.statusCode() == 200) {
                repository.deleteById(d.getId());
            }
        });
    }

}
