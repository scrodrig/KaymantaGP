/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.AutentificacionServicio;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@SessionScoped
public class InicioBean implements Serializable {

    /**
     * Creates a new instance of IncioBean
     */
    @EJB
    private AutentificacionServicio autentificacionServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    private String nombreUsuario;
    private String nombreEmpleado;
    private String clave;
    private List<Modulo> modulos;
    private Rol rol;
    private Boolean permiteVista;

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
                    nombreEmpleado = empleado.getNombre();
                    usuario.setFechaUltAcceso(new Date());
                    usuarioServicio.actualizar(usuario);
                    return "seleccionrol";
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

    public String verSubMenu(String modulo) {
        if (modulo.equals("Administración")) {
            return "ui-icon-document";
        } else if (modulo.equals("Reportes")) {
            return "ui-icon-document";
        } else if (modulo.equals("Seguridades")) {
            return "ui-icon-locked";
        } else if (modulo.equals("Proyectos")) {
            return "ui-icon-pencil";
        } else if (modulo.equals("Buscador de Documentos")) {
            return "ui-icon-search";
        } else if (modulo.equals("Seguimiento")) {
            return "ui-icon-gear";
        }

        return "ui-icon-document";

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

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Boolean getPermiteVista() {
        return permiteVista;
    }

    public void setPermiteVista(Boolean permiteVista) {
        this.permiteVista = permiteVista;
    }
}
