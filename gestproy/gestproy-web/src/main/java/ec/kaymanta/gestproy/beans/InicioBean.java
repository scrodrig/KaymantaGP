/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.AutentificacionServicio;
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
    private String codUsuario;
    private String clave;
    @EJB
    private AutentificacionServicio autentificacionServicio;
    //@EJB
    //pri

    public String validarUsuario() {
        Usuario usuario = this.autentificacionServicio.UsuarioAutentificar(codUsuario, clave);
        Empleado empleado;
        System.out.println("En el BB " + usuario);
        if (usuario != null) {
            //if (usuario.getCodigo()) {
//                return "menu";
//            } else {
//                return "menuAdmin";
//            }
            return "menu";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login Incorrecto", "No coincide la informaci�n!"));
            return "inicio";
        }
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }
}
