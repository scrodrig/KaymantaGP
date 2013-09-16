/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.UsuarioDAO;
import ec.kaymanta.gestproy.modelo.Usuario;
import java.util.Date;
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
public class UsuarioServicio {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private UsuarioDAO usuarioDAO;
    
     /**
     *Función para obtener todos los registros existentes
     * @return
     */
    public List<Usuario> obtenerUsuarios() {
        return this.usuarioDAO.findAll();
    }
    
    
    
    /**
     * Función para crear nuevos registros
     * @param usuario
     */
    public void crear(Usuario usuario) {
        System.out.println("En crear "+usuario.getUsuario());
        usuario.setFechaUltAcceso(new Date());
        this.usuarioDAO.insert(usuario);
    }
       
    /**
     *Función para modificar registros existentes
     * @param usuario
     */
    public void actualizar(Usuario usuario) {
        System.out.println("En actualizar "+usuario.getUsuario());
        usuario.setFechaUltAcceso(new Date());
        this.usuarioDAO.update(usuario);
    }
            
    /**
     *Función para eliminar registros
     * @param usuario
     */
    public void eliminar(Usuario usuario) {
        System.out.println("En eliminar "+usuario.getUsuario());
        this.usuarioDAO.remove(usuario);
    }
    
    

}
