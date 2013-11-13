/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ActividadDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.Proyecto;
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
public class ActividadServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private ActividadDAO actividadDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Actividad> obtener() {
        return this.actividadDAO.findAll();
    }
    
     /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Actividad> findByProyecto(Proyecto proyecto) {
        return this.actividadDAO.findByProyecto(proyecto);
    }
    
    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Actividad> findByProyectoAndActividad(Proyecto proyecto, Actividad actividad) {
        return this.actividadDAO.findByProyectoAndActividad(proyecto,actividad);
    }

    /**
     * Función para obtener el registro de un actividad
     *
     * @param codigo
     * @return
     */
    public Actividad findByID(Long codigo) {
        return this.actividadDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param actividad
     */
    public void crear(Actividad actividad) {
        System.out.println("En crear " + actividad.getNombreActividad());
        //actividad.getFechaUltAcceso(new Date());
        this.actividadDAO.insert(actividad);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param actividad
     */
    public void actualizar(Actividad actividad) {
        System.out.println("En actualizar " + actividad.getNombreActividad());
        //actividad.setFechaUltAcceso(new Date());
        this.actividadDAO.update(actividad);
    }

    /**
     * Función para eliminar registros
     *
     * @param actividad
     */
    public void eliminar(Actividad actividad) {
        System.out.println("En eliminar " + actividad.getNombreActividad());
        Actividad proyectoTmp = this.actividadDAO.findById(actividad.getCodigo(), false);
        this.actividadDAO.remove(proyectoTmp);
    }


}
