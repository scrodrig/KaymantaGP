/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class UsuariosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of UsuariosBean
     */
    @EJB
    private UsuarioServicio usuarioServicio;
    private List<Usuario> usuarios;
    private Usuario usuario;
    private Usuario usuarioSeleccionado;
    private Usuario respaldo;
    private Usuario usrSesion;
    
    private Usuario usrAuditoria;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.usuarios = this.usuarioServicio.obtenerUsuarios();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.usuario = new Usuario();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        usuario = new Usuario();
        MensajesGenericos.infoCancelar();
    }
    
     public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        usuario = new Usuario();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.usuario=new Usuario();
            this.usuario = (Usuario) BeanUtils.cloneBean(this.usuarioSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getUsrAuditoria(String usr) {
        if (usr == null || "".equals(usr)) {
            return "";
        } else {
            System.out.println(usr);
            System.out.println(usuarioServicio.findByID(usr));
            try {
                 usuarioServicio.findByID(usr);
                 return usuarioServicio.findByID(usr).getUsuario();
            } catch (NullPointerException e) {
                return "";
            }
        }
    } 

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.usuario.setUsrCreacion(usrSesion.getCodigo());
                this.usuario.setFcreacion(new Date());
                this.usuarioServicio.crear(this.usuario);
                this.usuarios.add(this.usuario);
                MensajesGenericos.infoCrear("Usuario", this.usuario.getCodigo().toString().concat(" - ").concat(this.usuario.getUsuario()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.usuarios.indexOf(this.usuario);
                this.usuarioServicio.actualizar(this.usuario);
                usuarios.set(i, this.usuario);
                MensajesGenericos.infoModificar("Usuario", this.usuario.getCodigo().toString().concat(" - ").concat(this.usuario.getUsuario()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.usuario = new Usuario();
        try {
            this.usuario = (Usuario) BeanUtils.cloneBean(this.usuarioSeleccionado);
            this.usuario.setUsrModificacion(usrSesion.getCodigo());
            this.usuario.setFmodificacion(new Date());
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.usuarioSeleccionado);
        this.usuarioServicio.eliminar(this.usuarioSeleccionado);
        this.usuarios.remove(this.usuarioSeleccionado);
        MensajesGenericos.infoEliminar("Usuario", this.usuario.getCodigo().toString().concat(" - ").concat(this.usuario.getUsuario()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (usuarioSeleccionado instanceof Usuario) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public Usuario getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Usuario respaldo) {
        this.respaldo = respaldo;
    }

    public Usuario getUsrSesion() {
        return usrSesion;
    }

    public void setUsrSesion(Usuario usrSesion) {
        this.usrSesion = usrSesion;
    }

    public Usuario getUsrAuditoria() {
        return usrAuditoria;
    }

    public void setUsrAuditoria(Usuario usrAuditoria) {
        this.usrAuditoria = usrAuditoria;
    }

    
}
