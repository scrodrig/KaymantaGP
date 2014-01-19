/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ActividadEmpleadoDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEmpleado;
import ec.kaymanta.gestproy.modelo.ActividadEmpleadoPK;
import ec.kaymanta.gestproy.modelo.Empleado;
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
public class ActividadEmpleadoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private ActividadEmpleadoDAO actividadEmpleadoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<ActividadEmpleado> obtener() {
        return this.actividadEmpleadoDAO.findAll();
    }
    

    
    
    /**
     * Función para obtener el registro de un actividadEmpleado
     *
     * @param codigo
     * @return
     */
    public ActividadEmpleado findByID(ActividadEmpleadoPK codigo) {
        return this.actividadEmpleadoDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param actividadEmpleado
     */
    public void crear(ActividadEmpleado actividadEmpleado) {
        //actividadEmpleado.getFechaUltAcceso(new Date());
        this.actividadEmpleadoDAO.insert(actividadEmpleado);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param actividadEmpleado
     */
    public void actualizar(ActividadEmpleado actividadEmpleado) {
        //actividadEmpleado.setFechaUltAcceso(new Date());
        this.actividadEmpleadoDAO.update(actividadEmpleado);
    }
    
    
    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<ActividadEmpleado> findBySubActividad(Actividad actividad) {
        return this.actividadEmpleadoDAO.findBySubActividad(actividad);
    }
    
    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<ActividadEmpleado> findBySubActividadAndEmpleado(Actividad actividad, Empleado empleado) {
        return this.actividadEmpleadoDAO.findBySubActividadAndEmpleado(actividad,empleado);
    }

    /**
     * Función para eliminar registros
     *
     * @param actividadEmpleado
     */
    public void eliminar(ActividadEmpleado actividadEmpleado) {
        ActividadEmpleado proyectoTmp = this.actividadEmpleadoDAO.findById(actividadEmpleado.getPk(), false);
        this.actividadEmpleadoDAO.remove(proyectoTmp);
    }


}
