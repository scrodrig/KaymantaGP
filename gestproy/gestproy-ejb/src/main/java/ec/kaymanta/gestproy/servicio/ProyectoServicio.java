/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ProyectoDAO;
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
public class ProyectoServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private ProyectoDAO proyectoDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Proyecto> obtener() {
        return this.proyectoDAO.findAll();
    }

    /**
     * Función para obtener el registro de un proyecto
     *
     * @param codigo
     * @return
     */
    public Proyecto findByID(Long codigo) {
        return this.proyectoDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param proyecto
     */
    public void crear(Proyecto proyecto) {
        System.out.println("En crear " + proyecto.getNombreProyecto());
        //proyecto.getFechaUltAcceso(new Date());
        this.proyectoDAO.insert(proyecto);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param proyecto
     */
    public void actualizar(Proyecto proyecto) {
        System.out.println("En actualizar " + proyecto.getNombreProyecto());
        //proyecto.setFechaUltAcceso(new Date());
        this.proyectoDAO.update(proyecto);
    }

    /**
     * Función para eliminar registros
     *
     * @param proyecto
     */
    public void eliminar(Proyecto proyecto) {
        System.out.println("En eliminar " + proyecto.getNombreProyecto());
        Proyecto proyectoTmp = this.proyectoDAO.findById(proyecto.getCodigo(), false);
        this.proyectoDAO.remove(proyectoTmp);
    }

}
