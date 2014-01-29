/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.FuncionalidadDAO;
import ec.kaymanta.gestproy.dao.RolFuncionalidadDAO;
import ec.kaymanta.gestproy.modelo.Funcionalidad;
import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.RolFuncionalidad;
import java.util.AbstractList;
import java.util.ArrayList;
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
    private FuncionalidadDAO funcionalidadDAO;
    @EJB
    private RolFuncionalidadDAO rolFuncionalidadDAO;

    /**
     * Función para obtener todos los registros existentes
     *
     * @return
     */
    public List<Funcionalidad> obtener() {
        return this.funcionalidadDAO.findAll();
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public Funcionalidad findByID(Long codigo) {
        return this.funcionalidadDAO.findById(codigo, false);
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<Funcionalidad> fingByModulo(Modulo modulo) {
        return this.funcionalidadDAO.getByModulo(modulo);
    }

    /**
     * Función para obtener el registro de un empleado
     *
     * @param codigo
     * @return
     */
    public List<Funcionalidad> fingByModulo(Modulo modulo, Rol rol) {
        List<Funcionalidad> funcionalidadesM = this.funcionalidadDAO.getByModulo(modulo);
        List<Funcionalidad> funcionalidadesR = new ArrayList<Funcionalidad>();
        List<RolFuncionalidad> rfuncionalidadR = this.rolFuncionalidadDAO.findByRol(rol);
        for (int i = 0; i < rfuncionalidadR.size(); i++) {
            funcionalidadesR.add(rfuncionalidadR.get(i).getFuncionalidad());
        }

        for (int i = 0; i < rfuncionalidadR.size(); i++) {
            if (!funcionalidadesM.contains(funcionalidadesR.get(i))) {
                funcionalidadesM.remove(funcionalidadesR.get(i));
            }
        }
        return funcionalidadesM;
    }

    /**
     * Función para crear nuevos registros
     *
     * @param funcionalidad
     */
    public void crear(Funcionalidad funcionalidad) {
        this.funcionalidadDAO.insert(funcionalidad);
    }

    /**
     * Función para modificar registros existentes
     *
     * @param funcionalidad
     */
    public void actualizar(Funcionalidad funcionalidad) {
        this.funcionalidadDAO.update(funcionalidad);
    }

    /**
     * Función para eliminar registros
     *
     * @param funcionalidad
     */
    public void eliminar(Funcionalidad funcionalidad) {
        Funcionalidad funcionalidadTmp = this.funcionalidadDAO.findById(funcionalidad.getCodigo(), false);
        this.funcionalidadDAO.remove(funcionalidadTmp);
    }
}
