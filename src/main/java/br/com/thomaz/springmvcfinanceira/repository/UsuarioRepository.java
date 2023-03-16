package br.com.thomaz.springmvcfinanceira.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.thomaz.springmvcfinanceira.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{
    
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByDataCriacaoLessThan(LocalDate data);

}
