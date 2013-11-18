/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.EntregableDocumentoDAO;
import ec.kaymanta.gestproy.modelo.Actividad;
import ec.kaymanta.gestproy.modelo.ActividadEntregable;
import ec.kaymanta.gestproy.modelo.EntregableDocumento;
import ec.kaymanta.gestproy.modelo.EntregableDocumentoPK;
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
public class EntregableDocumentoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private EntregableDocumentoDAO entregableDocumentoDAO;

    /**
     * Ejecuta b�squedas sobre la entidad EntregableDocumento de acuerdo a las
     * condiciones especificadas.
     *
     * @param resultado Resultado de la b�squeda anterior.
     * @param entregableDocumento Objeto que define las condiciones de b�squeda.
     * @param order Cadena opcional utilizada para definir el orden de
     * despliegue de los resultados.
     * @return Resultado de la b�squeda.
     */
//    public ResultadoBusqueda<EntregableDocumento> busqueda(ResultadoBusqueda<EntregableDocumento> resultado, EntregableDocumento entregableDocumento, String... order) {
//        return this.documentosProyectoDAO.find(entregableDocumento, resultado, order);
//    }
    /**
     * Crea un nuevo registro perteneciente a la entidad: EntregableDocumento.
     *
     * @param entregableDocumento Objeto que contiene la informaci�n del registro
     * a ser insertado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void crear(EntregableDocumento entregableDocumento) {
        this.entregableDocumentoDAO.insert(entregableDocumento);
    }

    /**
     * Modifica un registro perteneciente a la entidad: EntregableDocumento.
     *
     * @param entregableDocumento Objeto que contiene la informaci�n del registro
     * a ser modificado.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void actualizar(EntregableDocumento entregableDocumento) {
        this.entregableDocumentoDAO.update(entregableDocumento);
    }

    /**
     * Obtiene un objeto de acuerdo al valor de su clave primaria.
     *
     * @param pk Valor de la clave primaria para realizar la b�squeda del
     * objeto.
     * @return Objeto de tipo EntregableDocumento cuya clave primaria es igual a
     * la recibida en el par�metro pk.
     */
    public EntregableDocumento obtenerPorId(EntregableDocumentoPK pk) {
        return this.entregableDocumentoDAO.findById(pk, true);
    }

    /**
     * Obtiene todas las parroquias de acuerdo a una provincia y a un canton.
     *
     * @param provincia Codigo de la provincia.
     * @param canton C�digo del cant�n.
     * @return Listado de parroquias.
     */
    public List<EntregableDocumento> findBySubActividad(ActividadEntregable entregable) {
        System.out.println("ESTOY EN DAO y EL PROYECTO ES "+ entregable.getNombreEntregable() + entregable.getActividad().getNombreActividad());
        return this.entregableDocumentoDAO.findBySubActividad(entregable);
    }

}
