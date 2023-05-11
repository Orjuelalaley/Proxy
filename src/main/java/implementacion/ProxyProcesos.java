/*
 * Asignatura: Patrones de Diseño de Software
 * Patrón Estructural - > Proxy
 * Tipo de Clase: Java
 */
package implementacion;
import servicios.Auditoria;
import servicios.Seguridad;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ProxyProcesos implements InterfaceProcesos {

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        Seguridad securityService = new Seguridad();
        if(!securityService.Autorizacion(Usuario, Password)){
            throw new Exception("El usuario '"+Usuario
                    +"' no tiene privilegios para ejecutar el proceso");
        }

        ProcesoDefecto ejecutorProcess = new ProcesoDefecto();
        ejecutorProcess.EjecutarProcesos(IdProceso, Usuario, Password);

        Auditoria audit = new Auditoria();
        audit.AuditoriaServicioUsado(Usuario, ProcesoDefecto.class.getName());
    }

}