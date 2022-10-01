package br.com.thomaz.springmvcfinanceira.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.thomaz.springmvcfinanceira.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    Optional<Usuario> findByEmail(String email);

}
