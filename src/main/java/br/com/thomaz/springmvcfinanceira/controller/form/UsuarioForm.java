package br.com.thomaz.springmvcfinanceira.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.com.thomaz.springmvcfinanceira.model.Usuario;

public class UsuarioForm {
    
    @NotBlank @Email
    private String email;
    @NotBlank @Length(min = 6, max = 20)
    private String senha;
    @NotBlank
    private String nome;
    
    public Usuario toUser(BCryptPasswordEncoder encoder) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setUsername(email);
        usuario.setPassword(encoder.encode(senha));
        return usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
