/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package femcoworking.servidor.Persistence;
import femcoworking.servidor.Models.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author fernando
 */
public interface ReservaRepository extends JpaRepository<Reserva, String>, JpaSpecificationExecutor<Reserva> {
    
    Reserva findByIdReserva(String idReserva);
}
