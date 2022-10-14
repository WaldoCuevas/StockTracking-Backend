package tup.stockTracking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tup.stockTracking.Models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
