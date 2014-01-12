/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.HistorialDocumentoDAO;
import ec.kaymanta.gestproy.modelo.HistorialDocumento;
import ec.kaymanta.gestproy.modelo.HistorialDocumentoPK;
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
public class HistorialDocumentoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private HistorialDocumentoDAO historialDocumentoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<HistorialDocumento> obtener() {
        return this.historialDocumentoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un historialDocumento
     *
     * @param codigo
     * @return
     */
    public HistorialDocumento findByID(HistorialDocumentoPK codigo) {
        return this.historialDocumentoDAO.findById(codigo, false);
    }
    
    /**
     * Función para obtener los historiales de un Documento
     *
     * @param codigo
     * @return
     */
    public List<HistorialDocumento> findByDocumento(Long codigo) {
        return this.historialDocumentoDAO.findByDocumento(codigo);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param historialDocumento
     */
    public void crear(HistorialDocumento historialDocumento) {
        this.historialDocumentoDAO.insert(historialDocumento);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param historialDocumento
     */
    public void actualizar(HistorialDocumento historialDocumento) {
        this.historialDocumentoDAO.update(historialDocumento);
    }

    /**
     * Función para eliminar registros
     *
     * @param historialDocumento
     */
    public void eliminar(HistorialDocumento historialDocumento) {
        HistorialDocumento proyectoTmp = this.historialDocumentoDAO.findById(historialDocumento.getPk(), false);
        this.historialDocumentoDAO.remove(proyectoTmp);
    }

}
