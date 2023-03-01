package tup.stockTracking.Productos.Models;


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
// import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "unidades")
public class Unidad {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_unidad")
    private String nombre_unidad; //nombre del tipo de unidad

    @OneToMany(mappedBy = "unidad")  // referencia a la instancia "unidad" en la clase Producto
    //@JsonManagedReference
    @JsonBackReference
    private List<Producto> producto;
}
