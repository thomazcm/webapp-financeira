package br.com.thomaz.springmvcfinanceira.controller.dto;

import br.com.thomaz.springmvcfinanceira.model.Usuario;

public class UsuarioDto {

    private String email;
    private String senha;
    private String nome;
    
    public UsuarioDto(Usuario usuario) {
        this.email = usuario.getUsername();
        this.senha = usuario.getPassword();
        this.nome = usuario.getNome();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }
    
}
