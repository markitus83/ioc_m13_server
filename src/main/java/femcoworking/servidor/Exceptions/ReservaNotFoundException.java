package femcoworking.servidor.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepció utilitzada per informar que la operació que vol fer el client no es
 * pot realitzar ja que no s'ha trobat cap usuari amb el identificador d'usuari 
 * sobre el que es vol fer l'opaeració. (error 404)
 * 
 * @author Fernando Puertas
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String idReserva)  {
        super("No s'ha trobat cap reserva amb l'identificador " + idReserva);
    }
}
