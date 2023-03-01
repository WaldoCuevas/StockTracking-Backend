package tup.stockTracking.Usuarios.Service;

import tup.stockTracking.Usuarios.Models.Rol;
import tup.stockTracking.Usuarios.Enums.RolNombre;
import tup.stockTracking.Usuarios.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    public void save(Rol rol) {
        rolRepository.save(rol);
    }
    
}
