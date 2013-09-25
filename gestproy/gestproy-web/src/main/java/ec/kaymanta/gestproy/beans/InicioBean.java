/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.AutentificacionServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import java.io.Serializable;
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

    public String validarUsuario() {
        Usuario usuario = this.autentificacionServicio.usuarioAutentificar(nombreUsuario, clave);
        System.out.println("ESTOY EN validarUsuario");
        System.out.println("En el BB " + usuario);
        if (usuario != null) {
            Empleado empleado = this.empleadoServicio.findByID(usuario.getCodigo());
            System.out.println("En el BB " + empleado);
            if (empleado != null) {
                System.out.println("En el BB " + usuario);
                 if (empleado.getCodigo().equals(usuario.getCodigo())) {
                   return "menu";
               } else {
                   return "menuAdmin";
               }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información!"));
                return "inicio";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la información!"));
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
