/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.UsuarioRolDAO;
import ec.kaymanta.gestproy.modelo.UsuarioRol;
import ec.kaymanta.gestproy.modelo.UsuarioRolPK;
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
public class UsuarioRolServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UsuarioRolDAO usuarioRolDAO;
    
     /**
     *Función para obtener todos los registros existentes
     * @return
     */
    public List<UsuarioRol> obtenerUsuarios() {
        return this.usuarioRolDAO.findAll();
    }
    
    
     /**
     *Función para obtener el registro de un usuarioRol
     * @param codigo
     * @return
     */
    public UsuarioRol findByID(UsuarioRolPK codigo) {
        return this.usuarioRolDAO.findById(codigo,false);
    }
    

    /**
     * Función para crear nuevos registros
     * @param usuarioRol
     */
    public void crear(UsuarioRol usuarioRol) {
        System.out.println("En crear "+usuarioRol.getUsuario());
        //UsuarioRol ur =this.usuarioRolDAO.findById(usuarioRol.getPk(), false);
        //usuarioRol.setFechaUltAcceso(new Date());
        this.usuarioRolDAO.insert(usuarioRol);
    }
       
    /**
     *Función para modificar registros existentes
     * @param usuarioRol
     */
    public void actualizar(UsuarioRol usuarioRol) {
        System.out.println("En actualizar "+usuarioRol.getUsuario());
        //UsuarioRol ur =this.usuarioRolDAO.findById(usuarioRol.getPk(), false);
        this.usuarioRolDAO.update(usuarioRol);
    }
            
    /**
     *Función para eliminar registros
     * @param usuarioRol
     */
    
    public void eliminar(UsuarioRol usuarioRol) {
        System.out.println("En eliminar "+usuarioRol.getUsuario());
        UsuarioRol usuarioTmp = this.usuarioRolDAO.findById(usuarioRol.getPk(), false);
        this.usuarioRolDAO.remove(usuarioTmp);
    }

}
