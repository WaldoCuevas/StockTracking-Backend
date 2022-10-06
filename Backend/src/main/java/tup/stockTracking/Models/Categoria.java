package tup.stockTracking.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {
    
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre_categoria")
    private String nombre_categoria; //nombre de la categoria

    @OneToMany(mappedBy = "categoria") // referencia a la instancia "categoria" en la clase Producto
    //@JsonManagedReference
    @JsonBackReference
    private List<Producto> producto;
    
}
