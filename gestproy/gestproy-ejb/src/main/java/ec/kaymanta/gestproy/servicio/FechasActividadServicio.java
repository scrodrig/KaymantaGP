/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.FechasActividadDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.FechasActividad;
import ec.kaymanta.gestproy.modelo.FechasActividadPK;
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
public class FechasActividadServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private FechasActividadDAO fechasActividadDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<FechasActividad> obtener() {
        return this.fechasActividadDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public FechasActividad findByID(FechasActividadPK codigo) {
        return this.fechasActividadDAO.findById(codigo, false);
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public FechasActividad findLastByActividad(Actividad actividad) {
        return this.fechasActividadDAO.findLastByActividad(actividad);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param fechasActividad
     */
    public void crear(FechasActividad fechasActividad) {
        System.out.println("En crear " + fechasActividad.getPk());
        //empleado.getFechaUltAcceso(new Date());
        this.fechasActividadDAO.insert(fechasActividad);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param fechasActividad
     */
    public void actualizar(FechasActividad fechasActividad) {
        System.out.println("En actualizar " + fechasActividad.getPk());
        //empleado.setFechaUltAcceso(new Date());
        this.fechasActividadDAO.update(fechasActividad);
    }

    /**
     * Función para eliminar registros
     *
     * @param fechasActividad
     */
    public void eliminar(FechasActividad fechasActividad) {
        System.out.println("En eliminar " + fechasActividad.getPk().getActividad());
        FechasActividad rolFuncionalidadTmp = this.fechasActividadDAO.findById(fechasActividad.getPk(), false);
        this.fechasActividadDAO.remove(rolFuncionalidadTmp);
    }
}
