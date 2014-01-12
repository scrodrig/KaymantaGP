/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.TipoEntregableDAO;
import ec.kaymanta.gestproy.modelo.TipoEntregable;
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
public class TipoEntregableServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private TipoEntregableDAO tipoEntregableDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<TipoEntregable> obtener() {
        return this.tipoEntregableDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public TipoEntregable findByID(Long codigo) {
        return this.tipoEntregableDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param tipoEntregable
     */
    public void crear(TipoEntregable tipoEntregable) {
        this.tipoEntregableDAO.insert(tipoEntregable);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param tipoEntregable
     */
    public void actualizar(TipoEntregable tipoEntregable) {
        this.tipoEntregableDAO.update(tipoEntregable);
    }

    /**
     * Función para eliminar registros
     *
     * @param tipoEntregable
     */
    public void eliminar(TipoEntregable tipoEntregable) {
        TipoEntregable tipoEntregableTmp = this.tipoEntregableDAO.findById(tipoEntregable.getCodigo(), false);
        this.tipoEntregableDAO.remove(tipoEntregableTmp);
    }


}
