package tup.stockTracking.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tup.stockTracking.Models.Usuario;
import tup.stockTracking.Service.UsuarioService.UsuarioServiceImp;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImp UserService;

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuariosPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.UserService.getUserById(id));
    }

    @GetMapping("/usuarios")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return this.UserService.getAllUser();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.UserService.saveUser(usuario));
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

        Usuario usuarioActualizado = this.UserService.updateUser(usuario, id);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id) {

        this.UserService.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/credenciales")
    public ResponseEntity<Usuario> verificarCredenciales(@RequestBody Usuario requestCredencial) {

        if(this.UserService.verifyCredentials(requestCredencial)){
            return ResponseEntity.ok(requestCredencial);
        }
        return ResponseEntity.ok(null);
    } 

}