/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.RiesgoDAO;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Riesgo;
import ec.kaymanta.gestproy.modelo.RiesgoPK;
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
public class RiesgoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private RiesgoDAO riesgoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Riesgo> obtener() {
        return this.riesgoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Riesgo findByID(RiesgoPK codigo) {
        return this.riesgoDAO.findById(codigo, false);
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<Riesgo> findByProyecto(Proyecto proyecto) {
        return this.riesgoDAO.getByProyecto(proyecto);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param riesgo
     */
    public void crear(Riesgo riesgo) {
        System.out.println("En crear " + riesgo);
        //empleado.getFechaUltAcceso(new Date());
        this.riesgoDAO.insert(riesgo);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param riesgo
     */
    public void actualizar(Riesgo riesgo) {
        System.out.println("En actualizar " + riesgo);
        //empleado.setFechaUltAcceso(new Date());
        this.riesgoDAO.update(riesgo);
    }

    /**
     * Función para eliminar registros
     *
     * @param riesgo
     */
    public void eliminar(Riesgo riesgo) {
        System.out.println("En eliminar " + riesgo);
        Riesgo empresaTmp = this.riesgoDAO.findById(riesgo.getPk(), false);
        this.riesgoDAO.remove(empresaTmp);
    }

}
