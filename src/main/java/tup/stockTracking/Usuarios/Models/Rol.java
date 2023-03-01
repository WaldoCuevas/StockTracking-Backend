package tup.stockTracking.Usuarios.Models;

import tup.stockTracking.Usuarios.Enums.RolNombre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Aqui es importante decirle a Spring. que RolNombre es un Enums de tipo de
     * Cadena. (revisar Enums.RolNombre.java)
     * por eso usamos la siguiente anotacion.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNombre rolNombre;

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
}
