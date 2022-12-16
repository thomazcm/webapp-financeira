package br.com.thomaz.springmvcfinanceira.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.thomaz.springmvcfinanceira.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
    Optional<Usuario> findByEmail(String email);

}
