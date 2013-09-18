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

    public Usuario usuarioAutentificar(String nombreUsuario, String clave) {

        Usuario usuario = this.usuarioDAO.findByName(nombreUsuario);
        System.out.println("ESTOY EN AutentificacionServicio");
        if (usuario != null) {
            if (usuario.getClave().equals(clave)) {
                return usuario;
            }
        }
        return null;
    }
}
