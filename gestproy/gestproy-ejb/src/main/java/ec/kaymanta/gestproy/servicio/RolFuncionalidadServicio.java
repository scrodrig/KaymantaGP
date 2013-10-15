/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.RolFuncionalidadDAO;
import ec.kaymanta.gestproy.modelo.RolFuncionalidad;
import ec.kaymanta.gestproy.modelo.RolFuncionalidadPK;
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
public class RolFuncionalidadServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private RolFuncionalidadDAO rolFuncionalidadDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<RolFuncionalidad> obtener() {
        return this.rolFuncionalidadDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public RolFuncionalidad findByID(RolFuncionalidadPK codigo) {
        return this.rolFuncionalidadDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param rolFuncionalidad
     */
    public void crear(RolFuncionalidad rolFuncionalidad) {
        System.out.println("En crear " + rolFuncionalidad.getPk());
        //empleado.getFechaUltAcceso(new Date());
        this.rolFuncionalidadDAO.insert(rolFuncionalidad);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param rolFuncionalidad
     */
    public void actualizar(RolFuncionalidad rolFuncionalidad) {
        System.out.println("En actualizar " + rolFuncionalidad.getPk());
        //empleado.setFechaUltAcceso(new Date());
        this.rolFuncionalidadDAO.update(rolFuncionalidad);
    }

    /**
     * Función para eliminar registros
     *
     * @param rolFuncionalidad
     */
    public void eliminar(RolFuncionalidad rolFuncionalidad) {
        System.out.println("En eliminar " + rolFuncionalidad.getPk().getRol());
        RolFuncionalidad rolFuncionalidadTmp = this.rolFuncionalidadDAO.findById(rolFuncionalidad.getPk(), false);
        this.rolFuncionalidadDAO.remove(rolFuncionalidadTmp);
    }


}
