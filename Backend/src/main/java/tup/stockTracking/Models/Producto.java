package tup.stockTracking.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")   
    private String nombre;

    @Column(name = "Descripcion")
    private String descripcion;

    @Column(name = "Cantidad")
    private float cantidad;

    @Column(name = "estado")
    private Boolean estado = true;

    @ManyToOne(cascade = CascadeType.ALL)  
    @JoinColumn(name = "id_categoria")
    //@JsonBackReference
    //@JsonManagedReference
    private Categoria categoria;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_unidad")
    //@JsonBackReference
    //@JsonManagedReference
    private Unidad unidad;


    //La relación Hibernate @OneToMany provoca un bucle infinito o entradas vacías en el resultado JSON
    /*
    Solucion: 
    https://stackoverflow.com/questions/16577907/hibernate-onetomany-relationship-causes-infinite-loop-or-empty-entries-in-json
    */
}
