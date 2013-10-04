/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.InteresadoDAO;
import ec.kaymanta.gestproy.modelo.Interesado;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author schubert_david
 */
@Stateless
@LocalBean
public class InteresadoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private InteresadoDAO interesadoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Interesado> obtener() {
        return this.interesadoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Interesado findByID(Long codigo) {
        return this.interesadoDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param interesado
     */
    public void crear(Interesado interesado) {
        System.out.println("En crear " + interesado.getNombre());
        //empleado.getFechaUltAcceso(new Date());
        this.interesadoDAO.insert(interesado);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param interesado
     */
    public void actualizar(Interesado interesado) {
        System.out.println("En actualizar " + interesado.getNombre());
        //empleado.setFechaUltAcceso(new Date());
        this.interesadoDAO.update(interesado);
    }

    /**
     * Función para eliminar registros
     *
     * @param interesado
     */
    public void eliminar(Interesado interesado) {
        System.out.println("En eliminar " + interesado.getNombre());
        Interesado interesadoTmp = this.interesadoDAO.findById(interesado.getCodigo(), false);
        this.interesadoDAO.remove(interesadoTmp);
    }
    
}
