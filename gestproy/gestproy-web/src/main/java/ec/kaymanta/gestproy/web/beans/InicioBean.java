/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.AutentificacionServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class InicioBean implements Serializable {

    /**
     * Creates a new instance of IncioBean
     */
    private String nombreUsuario;
    private String clave;
    @EJB
    private AutentificacionServicio autentificacionServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;

    public String validarUsuario() {
        nombreUsuario = nombreUsuario.toLowerCase();
        Usuario usuario = this.autentificacionServicio.usuarioAutentificar(nombreUsuario, clave);
        if (usuario != null) {
            Empleado empleado = this.empleadoServicio.findByID(usuario.getCodigo());
            if (empleado != null) {
                if (empleado.getCodigo().equals(usuario.getCodigo())) {
                    usuario.setFechaUltAcceso(new Date());
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Empleado", empleado);
                    usuario.setFechaUltAcceso(new Date());
                    usuarioServicio.actualizar(usuario);
                    return "menu";
                } else {
                    return "inicio";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información"));
                return "inicio";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información"));
            return "inicio";
        }
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
