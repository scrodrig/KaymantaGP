/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ModuloDAO;
import ec.kaymanta.gestproy.modelo.Modulo;
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
public class ModuloServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private ModuloDAO moduloDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Modulo> obtener() {
        return this.moduloDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Modulo findByID(Long codigo) {
        return this.moduloDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param modulo
     */
    public void crear(Modulo modulo) {
        this.moduloDAO.insert(modulo);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param modulo
     */
    public void actualizar(Modulo modulo) {
        this.moduloDAO.update(modulo);
    }

    /**
     * Función para eliminar registros
     *
     * @param modulo
     */
    public void eliminar(Modulo modulo) {
        Modulo moduloTmp = this.moduloDAO.findById(modulo.getCodigo(), false);
        this.moduloDAO.remove(moduloTmp);
    }


}
