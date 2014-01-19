/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.modelo.UsuarioRol;
import ec.kaymanta.gestproy.modelo.UsuarioRolPK;
import ec.kaymanta.gestproy.servicio.AutentificacionServicio;
import ec.kaymanta.gestproy.servicio.UsuarioRolServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class SeleccionRolBean implements Serializable {

    /**
     * Creates a new instance of SeleccionRolBean
     */
    private String cabecera;
    private String codigoRol;
    private UsuarioRol usuarioRol;
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    @EJB
    private AutentificacionServicio autentificacionServicio;
    private Usuario usuario;
    private List<UsuarioRol> usuarioRoles;
    private List<Modulo> modulos;
    
    @PostConstruct
    public void postConstructor() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        usuario = (Usuario) session.getAttribute("Usuario");
        //this.cabecera = this.autenticacionServicio.obtenerCabecera(userSessionBean.getUsuario());
        this.usuarioRoles = this.usuarioRolServicio.getByUser(usuario);
    }
    
    public String seleccionarRol() {
        try {
            UsuarioRolPK pK = new UsuarioRolPK(usuario.getCodigo(), Long.valueOf(codigoRol));
            Rol rol = this.usuarioRolServicio.findByID(pK).getRol();            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Rol", rol);
            modulos = this.autentificacionServicio.obtenerMenuRol(rol);
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            InicioBean userSessionBean = (InicioBean) session.getAttribute("inicioBean");
            userSessionBean.setRol(rol);
            if (rol.getNombre().equals("Administrador") || rol.getNombre().equals("Desarrollador") || rol.getNombre().equals("Jefe Proyectos") || rol.getNombre().equals("Gerente")) {
                userSessionBean.setPermiteVista(Boolean.TRUE);
            } else {
                userSessionBean.setPermiteVista(Boolean.FALSE);
            }
            userSessionBean.setModulos(modulos);            
            return "menu?faces-redirect=true";
        } catch (Exception e) {
            return "inicio";
        }
    }
    
    public String getCabecera() {
        return cabecera;
    }
    
    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }
    
    public String getCodigoRol() {
        return codigoRol;
    }
    
    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }
    
    public UsuarioRol getUsuarioRol() {
        return usuarioRol;
    }
    
    public void setUsuarioRol(UsuarioRol usuarioRol) {
        this.usuarioRol = usuarioRol;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }
    
    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
    
    public List<Modulo> getModulos() {
        return modulos;
    }
    
    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }
}
