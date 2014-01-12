/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.DocumentoDAO;
import ec.kaymanta.gestproy.modelo.ActividadEntregable;
import ec.kaymanta.gestproy.modelo.Documento;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.EntregableDocumento;
import ec.kaymanta.gestproy.modelo.Proyecto;
import java.util.ArrayList;
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
public class DocumentoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private DocumentoDAO documentoDAO;
    @EJB
    private DocumentosProyectoServicio documentosProyectoServicio;
    @EJB
    private EntregableDocumentoServicio entregableDocumentoServicio;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Documento> obtener() {
        return this.documentoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Documento findByID(Long codigo) {
        return this.documentoDAO.findById(codigo, false);
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<Documento> findByProyecto(Proyecto proyecto) {
        List<DocumentosProyecto> documentosProyectos = new ArrayList<DocumentosProyecto>();
        List<Documento> documentos = new ArrayList<Documento>();
        documentosProyectos = this.documentosProyectoServicio.findByProyecto(proyecto);
        for (int i = 0; i < documentosProyectos.size(); i++) {
            documentos.add(documentosProyectos.get(i).getDocumento());
        }
        return documentos;
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<Documento> findBySubActividad(ActividadEntregable actividadEntregable) {
        List<EntregableDocumento> entregableDocumentos = new ArrayList<EntregableDocumento>();
        List<Documento> documentos = new ArrayList<Documento>();
        entregableDocumentos = this.entregableDocumentoServicio.findBySubActividad(actividadEntregable);
        for (int i = 0; i < entregableDocumentos.size(); i++) {
            documentos.add(entregableDocumentos.get(i).getDocumento());
        }
        return documentos;
    }

    /**
     * Función para crear nuevos registros
     *
     * @param documento
     */
    public void crear(Documento documento) {
        //empleado.getFechaUltAcceso(new Date());
        this.documentoDAO.insert(documento);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param documento
     */
    public void actualizar(Documento documento) {
        //empleado.setFechaUltAcceso(new Date());
        this.documentoDAO.update(documento);
    }

    /**
     * Función para eliminar registros
     *
     * @param documento
     */
    public void eliminar(Documento documento) {
        Documento documentoTmp = this.documentoDAO.findById(documento.getCodigo(), false);
        this.documentoDAO.remove(documentoTmp);
    }
}
