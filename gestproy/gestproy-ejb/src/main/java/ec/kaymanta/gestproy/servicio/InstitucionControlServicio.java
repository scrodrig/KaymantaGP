/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.InstitucionControlDAO;
import ec.kaymanta.gestproy.modelo.InstitucionControl;
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
public class InstitucionControlServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private InstitucionControlDAO institucionControlDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<InstitucionControl> obtener() {
        return this.institucionControlDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public InstitucionControl findByID(Long codigo) {
        return this.institucionControlDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param institucionControl
     */
    public void crear(InstitucionControl institucionControl) {
        this.institucionControlDAO.insert(institucionControl);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param institucionControl
     */
    public void actualizar(InstitucionControl institucionControl) {
        this.institucionControlDAO.update(institucionControl);
    }

    /**
     * Función para eliminar registros
     *
     * @param empresa
     */
    public void eliminar(InstitucionControl institucionControl) {
        InstitucionControl institucionControlTmp = this.institucionControlDAO.findById(institucionControl.getCodigo(), false);
        this.institucionControlDAO.remove(institucionControlTmp);
    }


}
