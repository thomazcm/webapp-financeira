package br.com.thomaz.springmvcfinanceira.controller.dto;

import br.com.thomaz.springmvcfinanceira.model.Usuario;

public class UsuarioDto {

    private String email;
    private String senha;
    
    public UsuarioDto(Usuario usuario) {
        this.email = usuario.getUsername();
        this.senha = usuario.getPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
    
}
