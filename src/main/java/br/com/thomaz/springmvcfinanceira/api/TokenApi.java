package br.com.thomaz.springmvcfinanceira.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.thomaz.springmvcfinanceira.service.ApiService;
import br.com.thomaz.springmvcfinanceira.service.TokenService;

@RestController
@RequestMapping("/api")
public class TokenApi {
    
    @Autowired private TokenService tokenService;
    
    @GetMapping("/token")
    public String getToken() {
        return tokenService.getToken();
    }
    
}
