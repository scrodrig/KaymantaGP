/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.GastoDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.Gasto;
import ec.kaymanta.gestproy.modelo.GastoPK;
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
public class GastoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private GastoDAO gastoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Gasto> obtener() {
        return this.gastoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Gasto findByID(GastoPK codigo) {
        return this.gastoDAO.findById(codigo, false);
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<Gasto> findBySubActividad(Actividad actividad) {
        return this.gastoDAO.findBySubActividad(actividad);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param gasto
     */
    public void crear(Gasto gasto) {
        //empleado.getFechaUltAcceso(new Date());
        this.gastoDAO.insert(gasto);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param gasto
     */
    public void actualizar(Gasto gasto) {
        //empleado.setFechaUltAcceso(new Date());
        this.gastoDAO.update(gasto);
    }

    /**
     * Función para eliminar registros
     *
     * @param gasto
     */
    public void eliminar(Gasto gasto) {
        Gasto empresaTmp = this.gastoDAO.findById(gasto.getPk(), false);
        this.gastoDAO.remove(empresaTmp);
    }
    
}
