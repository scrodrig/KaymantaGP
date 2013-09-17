/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;


import ec.kaymanta.gestproy.dao.UsuarioDAO;
import ec.kaymanta.gestproy.modelo.Usuario;
import javax.ejb.*;

/**
 *
 * @author schubert_david
 */
@Stateless
@LocalBean
public class AutentificacionServicio {

     @EJB
    private UsuarioDAO usuarioDAO;
    
   
     public Usuario UsuarioAutentificar(String codigoUsuario, String clave)
    {
        
        Usuario usuario =this.usuarioDAO.findById(codigoUsuario,true);
        if(usuario!=null && usuario.getClave().equals(clave))
            return usuario;
        return null;
    }
    
}
