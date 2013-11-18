/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ExpectativaDAO;
import ec.kaymanta.gestproy.modelo.Expectativa;
import ec.kaymanta.gestproy.modelo.ExpectativaPK;
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
public class ExpectativaServicio {

   // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private ExpectativaDAO expectativaDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Expectativa> obtener() {
        return this.expectativaDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Expectativa findByID(ExpectativaPK codigo) {
        return this.expectativaDAO.findById(codigo, false);
    }
    
    /**
     * Función para obtener el registro de un empleado
     *
     * @param proyecto
     * @return
     */
    public List<Expectativa> findByProyecto(Proyecto proyecto) {
        return this.expectativaDAO.getByProyecto(proyecto);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param expectativa
     */
    public void crear(Expectativa expectativa) {
        System.out.println("En crear " + expectativa);
        //empleado.getFechaUltAcceso(new Date());
        this.expectativaDAO.insert(expectativa);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param expectativa
     */
    public void actualizar(Expectativa expectativa) {
        System.out.println("En actualizar " + expectativa);
        //empleado.setFechaUltAcceso(new Date());
        this.expectativaDAO.update(expectativa);
    }

    /**
     * Función para eliminar registros
     *
     * @param expectativa
     */
    public void eliminar(Expectativa expectativa) {
        System.out.println("En eliminar " + expectativa);
        Expectativa empresaTmp = this.expectativaDAO.findById(expectativa.getPk(), false);
        this.expectativaDAO.remove(empresaTmp);
    }
}
