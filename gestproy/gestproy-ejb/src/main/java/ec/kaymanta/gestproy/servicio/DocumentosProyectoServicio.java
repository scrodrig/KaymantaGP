/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.DocumentosProyectoDAO;
import ec.kaymanta.gestproy.modelo.DocumentosProyecto;
import ec.kaymanta.gestproy.modelo.DocumentosProyectoPK;
import ec.kaymanta.gestproy.modelo.Proyecto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author schubert_david
 */
@Stateless
@LocalBean
public class DocumentosProyectoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private DocumentosProyectoDAO documentosProyectoDAO;

    /**
     * Ejecuta b�squedas sobre la entidad DocumentosProyecto de acuerdo a las
     * condiciones especificadas.
     *
     * @param resultado Resultado de la b�squeda anterior.
     * @param documentosProyecto Objeto que define las condiciones de b�squeda.
     * @param order Cadena opcional utilizada para definir el orden de
     * despliegue de los resultados.
     * @return Resultado de la b�squeda.
     */
//    public ResultadoBusqueda<DocumentosProyecto> busqueda(ResultadoBusqueda<DocumentosProyecto> resultado, DocumentosProyecto documentosProyecto, String... order) {
//        return this.documentosProyectoDAO.find(documentosProyecto, resultado, order);
//    }
    /**
     * Crea un nuevo registro perteneciente a la entidad: DocumentosProyecto.
     *
     * @param documentosProyecto Objeto que contiene la informaci�n del registro
     * a ser insertado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void crear(DocumentosProyecto documentosProyecto) {
        this.documentosProyectoDAO.insert(documentosProyecto);
    }

    /**
     * Modifica un registro perteneciente a la entidad: DocumentosProyecto.
     *
     * @param documentosProyecto Objeto que contiene la informaci�n del registro
     * a ser modificado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizar(DocumentosProyecto documentosProyecto) {
        this.documentosProyectoDAO.update(documentosProyecto);
    }

    /**
     * Obtiene un objeto de acuerdo al valor de su clave primaria.
     *
     * @param pk Valor de la clave primaria para realizar la b�squeda del
     * objeto.
     * @return Objeto de tipo DocumentosProyecto cuya clave primaria es igual a
     * la recibida en el par�metro pk.
     */
    public DocumentosProyecto obtenerPorId(DocumentosProyectoPK pk) {
        return this.documentosProyectoDAO.findById(pk, false);
    }

    /**
     * Obtiene todas las parroquias de acuerdo a una provincia y a un canton.
     *
     * @param provincia Codigo de la provincia.
     * @param canton C�digo del cant�n.
     * @return Listado de parroquias.
     */
    public List<DocumentosProyecto> findByProyecto(Proyecto proyecto) {
        return this.documentosProyectoDAO.findByProyecto(proyecto.getCodigo());
    }
}
