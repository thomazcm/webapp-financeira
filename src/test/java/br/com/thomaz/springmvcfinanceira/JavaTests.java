package br.com.thomaz.springmvcfinanceira;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import br.com.thomaz.springmvcfinanceira.controller.dto.UsuarioDto;
import br.com.thomaz.springmvcfinanceira.model.Usuario;
import br.com.thomaz.springmvcfinanceira.service.ApiService;

class JavaTests {

    @Test
    void test() {
        
        ApiService http = new ApiService();
        Usuario usuario = new Usuario();
        usuario.setEmail("thomazcm@gmail.com");
        usuario.setSenha("$2a$10$Lue05AzesyP1DhgjXbinseHiCOwJc3WQfB23l.anR5Y0pesIznHUS");
        var response = http.doRequest(HttpMethod.POST, "/usuarios", new UsuarioDto(usuario));
        System.out.println(response.statusCode());
    }

}
