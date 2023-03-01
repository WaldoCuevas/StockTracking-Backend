package tup.stockTracking.Security.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NuevoUsuario {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Email
    private String email;

    @NotBlank
    private Integer edad;

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;

    private Set<String> roles = new HashSet<>();

}
