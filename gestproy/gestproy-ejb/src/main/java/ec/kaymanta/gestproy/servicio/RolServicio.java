/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.RolDAO;
import ec.kaymanta.gestproy.modelo.Rol;
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
public class RolServicio {

// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private RolDAO rolDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Rol> obtener() {
        return this.rolDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Rol findByID(Long codigo) {
        return this.rolDAO.findById(codigo, false);
    }
    
    
    public Rol findByName(String nombre)
    {
        return this.rolDAO.findByName(nombre);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param rol
     */
    public void crear(Rol rol) {
        System.out.println("En crear " + rol.getNombre());
        //empleado.getFechaUltAcceso(new Date());
        this.rolDAO.insert(rol);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param rol
     */
    public void actualizar(Rol rol) {
        System.out.println("En actualizar " + rol.getNombre());
        //empleado.setFechaUltAcceso(new Date());
        this.rolDAO.update(rol);
    }

    /**
     * Función para eliminar registros
     *
     * @param rol
     */
    public void eliminar(Rol rol) {
        System.out.println("En eliminar " + rol.getNombre());
        Rol rolTmp = this.rolDAO.findById(rol.getCodigo(), false);
        this.rolDAO.remove(rolTmp);
    }

    
}
