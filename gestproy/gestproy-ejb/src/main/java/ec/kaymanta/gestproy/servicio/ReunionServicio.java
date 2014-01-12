/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ReunionDAO;
import ec.kaymanta.gestproy.modelo.Proyecto;
import ec.kaymanta.gestproy.modelo.Reunion;
import ec.kaymanta.gestproy.modelo.ReunionPK;
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
public class ReunionServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private ReunionDAO reunionDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Reunion> obtener() {
        return this.reunionDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Reunion findByID(ReunionPK codigo) {
        return this.reunionDAO.findById(codigo, false);
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param proyecto
     * @return
     */
    public List<Reunion> findByProyecto(Proyecto proyecto) {
        return this.reunionDAO.getByProyecto(proyecto);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param reunion
     */
    public void crear(Reunion reunion) {
        this.reunionDAO.insert(reunion);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param reunion
     */
    public void actualizar(Reunion reunion) {
        this.reunionDAO.update(reunion);
    }

    /**
     * Función para eliminar registros
     *
     * @param reunion
     */
    public void eliminar(Reunion reunion) {
        Reunion empresaTmp = this.reunionDAO.findById(reunion.getPk(), false);
        this.reunionDAO.remove(empresaTmp);
    }
}
