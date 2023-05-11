/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Java
 */

package servicios;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fabrizio Bolaño
 */
public class Auditoria {
    public void AuditoriaServicioUsado(String user, String servicio){
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date now = new Date();
        String today = new SimpleDateFormat("dd/MM/yyyy").format(now);
        System.out.println(user + " utilizó el servicio > "
                + servicio + ", a las " + formater.format(now));
        Validation3Days userValidation = new Validation3Days();
        userValidation.validarUsoServicio(user);
    }
}