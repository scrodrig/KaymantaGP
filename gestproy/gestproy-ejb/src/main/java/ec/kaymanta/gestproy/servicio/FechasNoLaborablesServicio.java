/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.FechasNoLaborablesDAO;
import ec.kaymanta.gestproy.modelo.FechasNoLaborables;
import java.util.Date;
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
public class FechasNoLaborablesServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private FechasNoLaborablesDAO fechasNoLaborablesDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<FechasNoLaborables> obtener() {
        return this.fechasNoLaborablesDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public FechasNoLaborables findByID(Date date) {
        return this.fechasNoLaborablesDAO.findById(date, false);
    }
    
    
    public FechasNoLaborables findByDate(Date date)
    {
        return this.fechasNoLaborablesDAO.findLastByActividad(date);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param fechasNoLaborables
     */
    public void crear(FechasNoLaborables fechasNoLaborables) {
        this.fechasNoLaborablesDAO.insert(fechasNoLaborables);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param fechasNoLaborables
     */
    public void actualizar(FechasNoLaborables fechasNoLaborables) {
        this.fechasNoLaborablesDAO.update(fechasNoLaborables);
    }

    /**
     * Función para eliminar registros
     *
     * @param fechasNoLaborables
     */
    public void eliminar(FechasNoLaborables fechasNoLaborables) {
        FechasNoLaborables rolTmp = this.fechasNoLaborablesDAO.findById(fechasNoLaborables.getFecha(), false);
        this.fechasNoLaborablesDAO.remove(rolTmp);
    }


}
