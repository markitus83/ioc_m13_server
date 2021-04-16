package femcoworking.servidor.Models;

/**
 * Representa l'identificador únic de l'usuari que es vol deshabilitar.
 * 
 * @author Fernando Puertas
 */
public class PeticioDeshabilitarUsuari {
    /**
     * Identificador únic de l'usuari que es vol deshabilitar.
     */
    private String idUsuari;

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }
}
