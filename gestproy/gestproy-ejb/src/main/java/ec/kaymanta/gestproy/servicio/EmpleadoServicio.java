/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.EmpleadoDAO;
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
public class EmpleadoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private EmpleadoDAO empleadoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Empleado> obtener() {
        return this.empleadoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Empleado findByID(String codigo) {
        return this.empleadoDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param empleado
     */
    public void crear(Empleado empleado) {
        //empleado.getFechaUltAcceso(new Date());
        this.empleadoDAO.insert(empleado);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param empleado
     */
    public void actualizar(Empleado empleado) {
        //empleado.setFechaUltAcceso(new Date());
        this.empleadoDAO.update(empleado);
    }

    /**
     * Función para eliminar registros
     *
     * @param empleado
     */
    public void eliminar(Empleado empleado) {
        Empleado empleadoTmp = this.empleadoDAO.findById(empleado.getCodigo(), false);
        this.empleadoDAO.remove(empleadoTmp);
    }
}
