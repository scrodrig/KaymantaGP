/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.FuncionalidadDAO;
import ec.kaymanta.gestproy.modelo.Funcionalidad;
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
public class FuncionalidadServicio {

@EJB
    private FuncionalidadDAO moduloDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Funcionalidad> obtener() {
        return this.moduloDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Funcionalidad findByID(Long codigo) {
        return this.moduloDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param funcionalidad
     */
    public void crear(Funcionalidad funcionalidad) {
        this.moduloDAO.insert(funcionalidad);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param funcionalidad
     */
    public void actualizar(Funcionalidad funcionalidad) {
        this.moduloDAO.update(funcionalidad);
    }

    /**
     * Función para eliminar registros
     *
     * @param funcionalidad
     */
    public void eliminar(Funcionalidad funcionalidad) {
        Funcionalidad funcionalidadTmp = this.moduloDAO.findById(funcionalidad.getCodigo(), false);
        this.moduloDAO.remove(funcionalidadTmp);
    }


}
