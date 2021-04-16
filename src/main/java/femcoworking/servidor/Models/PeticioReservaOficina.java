/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package femcoworking.servidor.Models;

/**
 *
 * @author fernando
 */
public class PeticioReservaOficina {
    private String codiAcces;
    private Reserva reserva;

    public String getCodiAcces() {
        return codiAcces;
    }

    public void setCodiAcces(String codiAcces) {
        this.codiAcces = codiAcces;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }   
}
    
