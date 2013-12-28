/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ActividadEntregableDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEntregable;
import ec.kaymanta.gestproy.modelo.ActividadEntregablePK;
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
public class ActividadEntregableServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private ActividadEntregableDAO actividadEntregableDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<ActividadEntregable> obtener() {
        return this.actividadEntregableDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public ActividadEntregable findByID(ActividadEntregablePK codigo) {
        return this.actividadEntregableDAO.findById(codigo, false);
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<ActividadEntregable> findBySubActividad(Actividad actividad) {
        return this.actividadEntregableDAO.findBySubActividad(actividad);
    }
    
    public ActividadEntregable findBySubActividadAndEntregable(Actividad subActividad, Long codActividadEntregable) {
        return this.actividadEntregableDAO.findBySubActividadAndEntregable(subActividad,codActividadEntregable);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param actividadEntregable
     */
    public void crear(ActividadEntregable actividadEntregable) {
        System.out.println("En crear " + actividadEntregable.getPk());
        //empleado.getFechaUltAcceso(new Date());
        this.actividadEntregableDAO.insert(actividadEntregable);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param actividadEntregable
     */
    public void actualizar(ActividadEntregable actividadEntregable) {
        System.out.println("En actualizar " + actividadEntregable.getPk());
        //empleado.setFechaUltAcceso(new Date());
        this.actividadEntregableDAO.update(actividadEntregable);
    }

    /**
     * Función para eliminar registros
     *
     * @param actividadEntregable
     */
    public void eliminar(ActividadEntregable actividadEntregable) {
        System.out.println("En eliminar " + actividadEntregable.getPk().getActividad());
        ActividadEntregable rolFuncionalidadTmp = this.actividadEntregableDAO.findById(actividadEntregable.getPk(), false);
        this.actividadEntregableDAO.remove(rolFuncionalidadTmp);
    }
}
