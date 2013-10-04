/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.EmpresaDAO;
import ec.kaymanta.gestproy.modelo.Empresa;
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
public class EmpresaServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private EmpresaDAO empresaDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Empresa> obtener() {
        return this.empresaDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Empresa findByID(String codigo) {
        return this.empresaDAO.findById(codigo, false);
    }

    /**
     * Función para crear nuevos registros
     *
     * @param empresa
     */
    public void crear(Empresa empresa) {
        System.out.println("En crear " + empresa.getRazonSocial());
        //empleado.getFechaUltAcceso(new Date());
        this.empresaDAO.insert(empresa);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param empresa
     */
    public void actualizar(Empresa empresa) {
        System.out.println("En actualizar " + empresa.getRazonSocial());
        //empleado.setFechaUltAcceso(new Date());
        this.empresaDAO.update(empresa);
    }

    /**
     * Función para eliminar registros
     *
     * @param empresa
     */
    public void eliminar(Empresa empresa) {
        System.out.println("En eliminar " + empresa.getRazonSocial());
        Empresa empresaTmp = this.empresaDAO.findById(empresa.getCodigo(), false);
        this.empresaDAO.remove(empresaTmp);
    }

}
