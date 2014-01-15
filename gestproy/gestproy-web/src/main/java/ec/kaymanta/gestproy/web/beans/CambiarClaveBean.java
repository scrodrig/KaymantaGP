/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class CambiarClaveBean implements Serializable {

    /**
     * Creates a new instance of CambiarClaveBean
     */
    private String nuevaClave;
    private String claveActual;
    private String confirmaNuevaClave;
    private String mensaje;
    private Boolean salir = Boolean.FALSE;
    private Boolean exito;
    @EJB
    private UsuarioServicio usuarioServicio;
    private Usuario usuario;
    
    

    @PostConstruct
    public void postConstructor() {
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
    }

    public void guardar(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuario.getClave().equals(this.claveActual)) {
            if (this.nuevaClave.equals(this.confirmaNuevaClave)) {
                usuario.setClave(this.confirmaNuevaClave);
                this.usuarioServicio.actualizar(usuario);
                this.salir=true;
                //context.addMessage(null, new FacesMessage("Información", "La clave ha sido cambiada."));
                mensaje="La clave ha sido cambiada exitosamente.";
                exito=true;
            } else {
                //context.addMessage(null, new FacesMessage("Error", "La nueva clave y la de confirmación no son las mismas."));
                mensaje="La nueva clave y la de confirmación no son las mismas.";
                exito=false;
            }
        } else {
            //context.addMessage(null, new FacesMessage("Error", "La clave actual no es correcta."));
            mensaje="La clave actual no es correcta.";
            exito=false;
        }

    }

    public void cancelar(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Información", "Se cancelo los cambios"));
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getConfirmaNuevaClave() {
        return confirmaNuevaClave;
    }

    public void setConfirmaNuevaClave(String confirmaNuevaClave) {
        this.confirmaNuevaClave = confirmaNuevaClave;
    }

    public Boolean getSalir() {
        return salir;
    }

    public void setSalir(Boolean salir) {
        this.salir = salir;
    }        

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }    

    public Boolean getExito() {
        return exito;
    }

    public void setExito(Boolean exito) {
        this.exito = exito;
    }
    
    
}
