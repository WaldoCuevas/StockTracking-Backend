package tup.stockTracking.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

    // Clase para verificar si el recurso solicitado existe o no.
    /*
     * Fuente
     * https://www.youtube.com/watch?v=o_HV_FCs-Z0
     */

}
