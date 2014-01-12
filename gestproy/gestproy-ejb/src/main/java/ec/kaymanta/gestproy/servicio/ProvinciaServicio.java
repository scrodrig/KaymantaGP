/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.ProvinciaDAO;
import ec.kaymanta.gestproy.modelo.Provincia;
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
public class ProvinciaServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @EJB
    private ProvinciaDAO provinciaDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Provincia> obtener() {
        return this.provinciaDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Provincia findByID(Long codigo) {
        return this.provinciaDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param provincia
     */
    public void crear(Provincia provincia) {
        this.provinciaDAO.insert(provincia);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param provincia
     */
    public void actualizar(Provincia provincia) {
        this.provinciaDAO.update(provincia);
    }

    /**
     * Función para eliminar registros
     *
     * @param provincia
     */
    public void eliminar(Provincia provincia) {
        Provincia moduloTmp = this.provinciaDAO.findById(provincia.getCodigo(), false);
        this.provinciaDAO.remove(moduloTmp);
    }
}
