package tup.stockTracking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tup.stockTracking.Models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
}
