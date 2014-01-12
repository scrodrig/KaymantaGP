/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.TipoDocumentoDAO;
import ec.kaymanta.gestproy.modelo.TipoDocumento;
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
public class TipoDocumentoServicio {

   // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private TipoDocumentoDAO tipoDocumentoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<TipoDocumento> obtener() {
        return this.tipoDocumentoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public TipoDocumento findByID(Long codigo) {
        return this.tipoDocumentoDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param tipoDocumento
     */
    public void crear(TipoDocumento tipoDocumento) {
        //empleado.getFechaUltAcceso(new Date());
        this.tipoDocumentoDAO.insert(tipoDocumento);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param tipoDocumento
     */
    public void actualizar(TipoDocumento tipoDocumento) {
        //empleado.setFechaUltAcceso(new Date());
        this.tipoDocumentoDAO.update(tipoDocumento);
    }

    /**
     * Función para eliminar registros
     *
     * @param tipoDocumento
     */
    public void eliminar(TipoDocumento tipoDocumento) {
        TipoDocumento tipoDocumentoTmp = this.tipoDocumentoDAO.findById(tipoDocumento.getCodigo(), false);
        this.tipoDocumentoDAO.remove(tipoDocumentoTmp);
    }

}
