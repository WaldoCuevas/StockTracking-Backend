package tup.stockTracking.Usuarios.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    @Column(unique = true)
    private String email; // No Puede existir 2 email de usuario iguales.

    @NotNull
    private Integer edad;

    @NotNull
    @Column(unique = true) // No Puede existir 2 nombres de usuario iguales.
    private String nombreUsuario;

    @NotNull
    private String password;

    /**
     * Se realiza una relacion de 'Muchos A Muchos' porque un usuario puede tener muchos roles
     * es decir, puede ser admin o user, o solamente user. (definido en Enums.RolNombre.java)
     * y vicesarsa. Los roles (Admin y user) pueden pertenecer a un usuario o a varios.
     * Ejemplo: 
     * usuario a = Admin user; 
     * usuario b = user;
     * usuario c = admin user; //este usuario tambien puede tener los roles de admin/user. o solo user.
     * Nota: Recomiendan usar Set/HashSet para relaciones @ManyToMany y listas.
     * Nota: En las relaciones ManyToMany es necesario crear una tabla intermedia.
     * En este ejemplo se llama 'usuario_rol'.
    */
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    /*
     * Aunque @Data de lombok me crea constructores, Getters y Setters.
     * necesitamos definir un constructor sin ID y sin los Roles.
     */
    public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull String email, @NotNull Integer edad,
            @NotNull String nombreUsuario, @NotNull String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.edad = edad;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    

}
