package tup.stockTracking.Productos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tup.stockTracking.Productos.Models.Unidad;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Long>{
    
}
