/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.LeccionesAprendidasDAO;
import ec.kaymanta.gestproy.modelo.LeccionesAprendidas;
import ec.kaymanta.gestproy.modelo.LeccionesAprendidasPK;
import ec.kaymanta.gestproy.modelo.Proyecto;
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
public class LeccionesAprendidasServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private LeccionesAprendidasDAO leccionesAprendidasDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<LeccionesAprendidas> obtener() {
        return this.leccionesAprendidasDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public LeccionesAprendidas findByID(LeccionesAprendidasPK codigo) {
        return this.leccionesAprendidasDAO.findById(codigo, false);
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param proyecto
     * @return
     */
    public List<LeccionesAprendidas> findByProyecto(Proyecto proyecto) {
        return this.leccionesAprendidasDAO.getByProyecto(proyecto);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param leccionesAprendidas
     */
    public void crear(LeccionesAprendidas leccionesAprendidas) {
        System.out.println("En crear " + leccionesAprendidas);
        //empleado.getFechaUltAcceso(new Date());
        this.leccionesAprendidasDAO.insert(leccionesAprendidas);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param leccionesAprendidas
     */
    public void actualizar(LeccionesAprendidas leccionesAprendidas) {
        System.out.println("En actualizar " + leccionesAprendidas);
        //empleado.setFechaUltAcceso(new Date());
        this.leccionesAprendidasDAO.update(leccionesAprendidas);
    }

    /**
     * Función para eliminar registros
     *
     * @param leccionesAprendidas
     */
    public void eliminar(LeccionesAprendidas leccionesAprendidas) {
        System.out.println("En eliminar " + leccionesAprendidas);
        LeccionesAprendidas empresaTmp = this.leccionesAprendidasDAO.findById(leccionesAprendidas.getPk(), false);
        this.leccionesAprendidasDAO.remove(empresaTmp);
    }

}
