/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Main()
 */

package patronproxy;
import implementacion.InterfaceProcesos;
import implementacion.FabricaServicios;
/**
 *
 * @author Fabrizio Bolaño
 */
public class PatronProxyMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String usuario = "fbolano";
        String password = "pds";
        int proceso = 1;
        InterfaceProcesos ProcesoActivo = FabricaServicios.CrearEjecucionProceso();
        try {
            ProcesoActivo.EjecutarProcesos(proceso, usuario, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}