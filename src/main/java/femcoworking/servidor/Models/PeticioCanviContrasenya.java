package femcoworking.servidor.Models;

/**
 * Representa la contrasenya que pot ser modificada per un usuari amb una petició al servidor.
 * 
 * @author Fernando Puertas
 */
public class PeticioCanviContrasenya {
    /**
     * Contrasenya de l'usuari.
     */
    private String contrasenya;
    
    /**
     * Obté la nova contrasenya introduïda per l'usuari.
     */
    public String getContrasenya() {
        return contrasenya;
    }
    
    /**
     * Actualitza la nova contrasenya introduïda per l'usuari.
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}
