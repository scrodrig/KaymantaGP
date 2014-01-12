/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.TipoGastoDAO;
import ec.kaymanta.gestproy.modelo.TipoGasto;
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
public class TipoGastoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private TipoGastoDAO tipoGastoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<TipoGasto> obtener() {
        return this.tipoGastoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public TipoGasto findByID(Long codigo) {
        return this.tipoGastoDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param tipoGasto
     */
    public void crear(TipoGasto tipoGasto) {
        this.tipoGastoDAO.insert(tipoGasto);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param tipoGasto
     */
    public void actualizar(TipoGasto tipoGasto) {
        this.tipoGastoDAO.update(tipoGasto);
    }

    /**
     * Función para eliminar registros
     *
     * @param tipoGasto
     */
    public void eliminar(TipoGasto tipoGasto) {
        TipoGasto tipoGastoTmp = this.tipoGastoDAO.findById(tipoGasto.getCodigo(), false);
        this.tipoGastoDAO.remove(tipoGastoTmp);
    }
        
}
