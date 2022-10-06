package tup.stockTracking.Service.UsuarioService;

import java.util.List;

import tup.stockTracking.Models.Usuario;

public interface UsuarioService {

    // Usuarios
    public Usuario getUserById(Long id);

    public List<Usuario> getAllUser();

    public Usuario saveUser(Usuario usuario);

    public Usuario updateUser(Usuario usuario, Long id);

    public void deleteUser(Long id);

    // Credenciales

    //public Credenciales getcredentialById(Long id);

    public boolean verifyCredentials(Usuario credencial);

}
