package tup.stockTracking.Usuarios.Service;

import tup.stockTracking.Usuarios.Models.Usuario;
import tup.stockTracking.Usuarios.Models.UsuarioPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * Este Clase de Servicio realiza varias funciones
 * 1. lo primero es que implementa una interfaz UserDetailService que nos crea el metodo 'loadUserByUsername';
 * 2. para obtener esos Detalles del usuario. usamos Inyeccion de dependencias al 'UsuarioService'.
 * 3. En la clase de UsuarioPrincipal. creamos un metodo build. lo cual nos verifica si el usuario es 
 *  admin o user. en caso de no ser ninguno de los 2. tira la excepcion 'UsernameNotFoundException';
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getNombreUsuario(nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }    

}
