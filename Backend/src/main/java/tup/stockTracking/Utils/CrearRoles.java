package tup.stockTracking.Utils;
/* 
package tup.stockTracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tup.stockTracking.Usuarios.Enums.RolNombre;
import tup.stockTracking.Usuarios.Models.Rol;
import tup.stockTracking.Usuarios.Service.RolService;

@Component
public class CrearRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    
    //Metodo que guarda en la db los roles que deben agregarse a cada usuario
    //Esta clase es de CommandLineRunner, es decir, que se ejecutara siempre que se inicie
    //el projecto de spring. por eso la clase debe comentarse o eliminarse luego de usarse una vez
    //sino siempre nos creara los roles en la db. 
     

     
    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNombre.ROLE_USER);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
    }
    

}

*/