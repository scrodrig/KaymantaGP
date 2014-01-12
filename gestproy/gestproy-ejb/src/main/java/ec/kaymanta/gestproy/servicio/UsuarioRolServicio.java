/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.UsuarioRolDAO;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.modelo.UsuarioRol;
import ec.kaymanta.gestproy.modelo.UsuarioRolPK;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Transient;

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
        usuarioRol.setFecha(new Date());
        this.usuarioRolDAO.insert(usuarioRol);
    }
       
    /**
     *Función para modificar registros existentes
     * @param usuarioRol
     */
    public void actualizar(UsuarioRol usuarioRol) {
        this.usuarioRolDAO.update(usuarioRol);
    }
            
    /**
     *Función para eliminar registros
     * @param usuarioRol
     */
    
    public void eliminar(UsuarioRol usuarioRol) {
        UsuarioRol usuarioTmp = this.usuarioRolDAO.findById(usuarioRol.getPk(), false);
        this.usuarioRolDAO.remove(usuarioTmp);
    }
    
    
    /**
     *
     * @param usuario
     * @return
     */
    @Transient
    public List<UsuarioRol> getByUser(Usuario usuario)
    {
        System.out.println("En consultar "+usuario.getUsuario());
        return this.usuarioRolDAO.findByUser(usuario);
    }

}
