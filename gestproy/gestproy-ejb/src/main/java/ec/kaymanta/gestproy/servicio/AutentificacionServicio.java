/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.servicio;

import ec.kaymanta.gestproy.dao.FuncionalidadDAO;
import ec.kaymanta.gestproy.dao.RolFuncionalidadDAO;
import ec.kaymanta.gestproy.dao.UsuarioDAO;
import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.RolFuncionalidad;
import ec.kaymanta.gestproy.modelo.RolFuncionalidadPK;
import ec.kaymanta.gestproy.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
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
    @EJB
    private RolFuncionalidadDAO rolFuncionalidadDAO;    
    @EJB
    private FuncionalidadDAO funcionalidadDAO;

    public Usuario usuarioAutentificar(String nombreUsuario, String clave) {

        Usuario usuario = this.usuarioDAO.findByName(nombreUsuario);
        if (usuario != null) {
            if (usuario.getClave().equals(clave)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Modulo> obtenerMenuRol(Rol rol) {

        List<Modulo> modulos = new ArrayList<Modulo>();
        RolFuncionalidad rolFuncionalidad = new RolFuncionalidad();
        RolFuncionalidadPK pk = new RolFuncionalidadPK();
        pk.setRol(rol.getCodigo());
        rolFuncionalidad.setPk(pk);
        List<RolFuncionalidad> funcionalidadesRolAll = this.rolFuncionalidadDAO.find(rolFuncionalidad);        
        for (RolFuncionalidad rolFuncionalidadT : funcionalidadesRolAll) {
            Modulo modulo = rolFuncionalidadT.getFuncionalidad().getModulo();
            modulo.setFuncionalidades(this.funcionalidadDAO.getByModulo(modulo));                        
            if (!modulos.contains(modulo)) {
                modulos.add(modulo);
            }
        }        
        return modulos;                
    }
}
