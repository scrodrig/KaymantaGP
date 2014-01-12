/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ActividadSegumientoDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadSegumiento;
import ec.kaymanta.gestproy.modelo.ActividadSegumientoPK;
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
public class ActividadSeguimientoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private ActividadSegumientoDAO actividadSeguimientoDAO;
    
    
    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<ActividadSegumiento> obtener() {
        return this.actividadSeguimientoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public ActividadSegumiento findByID(ActividadSegumientoPK codigo) {
        return this.actividadSeguimientoDAO.findById(codigo, false);
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<ActividadSegumiento> findBySubActividad(Actividad actividad) {
        return this.actividadSeguimientoDAO.findBySubActividad(actividad);
    }
    
    public ActividadSegumiento findBySubActividadAndResponsable(Actividad subActividad, String responsable) {
        return this.actividadSeguimientoDAO.findBySubActividadAndResponsable(subActividad,responsable);
    }
    
    public List<ActividadSegumiento> findByResponsable(String responsable) {
        return this.actividadSeguimientoDAO.findByResponsable(responsable);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param actividadSeguimiento
     */
    public void crear(ActividadSegumiento actividadSeguimiento) {
        //empleado.getFechaUltAcceso(new Date());
        this.actividadSeguimientoDAO.insert(actividadSeguimiento);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param actividadSeguimiento
     */
    public void actualizar(ActividadSegumiento actividadSeguimiento) {
        //empleado.setFechaUltAcceso(new Date());
        this.actividadSeguimientoDAO.update(actividadSeguimiento);
    }

    /**
     * Función para eliminar registros
     *
     * @param actividadSeguimiento
     */
    public void eliminar(ActividadSegumiento actividadSeguimiento) {
        ActividadSegumiento rolFuncionalidadTmp = this.actividadSeguimientoDAO.findById(actividadSeguimiento.getPk(), false);
        this.actividadSeguimientoDAO.remove(rolFuncionalidadTmp);
    }
    
}
