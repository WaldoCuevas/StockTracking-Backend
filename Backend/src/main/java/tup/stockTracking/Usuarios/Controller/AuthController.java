package tup.stockTracking.Usuarios.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tup.stockTracking.Usuarios.DTO.JwtDto;
import tup.stockTracking.Usuarios.DTO.LoginUsuario;
import tup.stockTracking.Usuarios.DTO.NuevoUsuario;
import tup.stockTracking.Usuarios.Enums.RolNombre;
import tup.stockTracking.Usuarios.JWT.JwtProvider;
import tup.stockTracking.Usuarios.Models.Rol;
import tup.stockTracking.Usuarios.Models.Usuario;
import tup.stockTracking.Usuarios.Service.RolService;
import tup.stockTracking.Usuarios.Service.UsuarioService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    // Controlador para obtener 1 usuario

    @GetMapping("/usuario/{nombreUsuario}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String nombreUsuario) {

        Usuario usuario = this.usuarioService.getNombreUsuario(nombreUsuario).get();

        return ResponseEntity.ok().body(usuario);
    }

    // Controlador para obtener todos los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok().body(this.usuarioService.getAllUsers());
    }

    // CREATE usuario
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario) {

        // 1.creamos un usuario con los datos que nos envia el cliente.
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getEmail(),
                nuevoUsuario.getEdad(), nuevoUsuario.getNombreUsuario(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));

        /*
         * 2. creamos un set y agregamos el rol de USER. (importante -> el set contiene
         * solo el rol de USER)
         * 2. si en los del cliente. contiene el rol de ADMIN, tambien lo
         * agregamos a la lista de Roles. (Importante -> Ahora el set contiene USER Y
         * ADMIN).
         */
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin"))
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());

        /*
         * 4. una vez terminada esa verificación, le agregamos/seteamos la lista de
         * Roles al usuario del cliente.
         * 5. Lo guardamos en la base de datos.
         * 6. y retornamos un mensaje 201 - CREATED.
        */
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    // LOGIN usuario
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario) {

        /*
         * Para realizar el login, lo primero es obtener las credenciales del cliente
         * y crear un objeto de Tipo Authentication
         */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));

        /*
         * con las credenciales en el objeto Authentication, usamos unos metodos de Security que necesitan
         * de parametro un objeto Authentication para validar esa authentication con credenciales.
         */

        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*
         * y ya, con esa authentificacion validada, generamos un token (es importante entender que
         * estamos con los datos del cliente, es decir que el authentication nunca sera el mismo,
         * por ende, el token tampoco. siempre se generara uno nuevo).
         */
        String jwt = jwtProvider.generateToken(authentication);

        /*
         * Mediante la authentication obtenemos unos ultimos datos del cliente
         * para realizar un ultimo paso.
         */
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        /*
         * DTO - Objeto de transferencia de datos - Data Transfer Object
         * Aqui es importante revisar la siguiente clase: JwtDTO.
         * esta clase contiene los siguiente valores: token, Bearer, nombreUsuario y
         * authorities;
         * Y es lo que permite realizar lo siguiente: Authorization Bearer
         * 
         * ---------------------------------Concepto----------------------------------------
         * 
         * Authorization Bearer es un tipo de modelos estándar de autorización OAuth
         * 2.0, que es “Bearer”. Un formato que nos permite la autorización en conjunto con
         * la autenticación de usuarios. Este es el esquema que está más de moda hoy en día
         * 
         * ------------------------------Fin del Concepto------------------------------------
         * 
         * JWT = Token
         * userDetails.getUsername() = usuario;
         * userDetails.getAuthorities() = autenticación
         */

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return ResponseEntity.ok().body(jwtDto);
    }

}
