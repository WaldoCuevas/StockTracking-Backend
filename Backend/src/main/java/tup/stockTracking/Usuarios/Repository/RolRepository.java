package tup.stockTracking.Usuarios.Repository;

import tup.stockTracking.Usuarios.Models.Rol;
import tup.stockTracking.Usuarios.Enums.RolNombre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
