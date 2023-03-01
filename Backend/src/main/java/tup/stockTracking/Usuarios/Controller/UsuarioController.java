package tup.stockTracking.Usuarios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tup.stockTracking.Security.DTO.NuevoUsuario;
import tup.stockTracking.Usuarios.Models.Usuario;
import tup.stockTracking.Usuarios.Service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {
    
@Autowired
private UsuarioService usuarioService;

// Controlador para obtener 1 usuario

@GetMapping("/usuario/{nombreUsuario}")
public ResponseEntity<NuevoUsuario> getUsuario(@PathVariable String nombreUsuario) {

    Usuario usuario = this.usuarioService.getNombreUsuario(nombreUsuario).get();
    
    NuevoUsuario nuevoUsuario = new NuevoUsuario();

    nuevoUsuario.setNombre(usuario.getNombre());
    nuevoUsuario.setApellido(usuario.getApellido());
    nuevoUsuario.setEdad(usuario.getEdad());
    nuevoUsuario.setEmail(usuario.getEmail());
    nuevoUsuario.setNombreUsuario(usuario.getNombreUsuario());
    nuevoUsuario.setPassword(usuario.getPassword());

    return ResponseEntity.ok().body(nuevoUsuario);
}

// Controlador para obtener todos los usuarios

@GetMapping("/usuarios")
public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
    return ResponseEntity.ok().body(this.usuarioService.getAllUsers());
}


}
