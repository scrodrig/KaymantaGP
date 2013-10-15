/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Rol;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.modelo.UsuarioRol;
import ec.kaymanta.gestproy.servicio.RolServicio;
import ec.kaymanta.gestproy.servicio.UsuarioRolServicio;
import ec.kaymanta.gestproy.servicio.UsuarioServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author schubert_david
 */
@ManagedBean
@ViewScoped
public class UsuariosRolBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of UsuariosRolBean
     */
    @EJB
    private UsuarioRolServicio usuarioRolServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private RolServicio rolServicio;
    private List<UsuarioRol> usuarioRoles;
    private UsuarioRol usuarioRolSeleccionado;
    private UsuarioRol respaldo;
    private UsuarioRol usuarioRol;
    private List<Usuario> usuarios;
    private List<Rol> roles;
    private String rol;
    private String usuario;

    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.usuarioRoles = this.usuarioRolServicio.obtenerUsuarios();
        this.usuarios = this.usuarioServicio.obtenerUsuarios();
        this.roles = this.rolServicio.obtener();
    }

    /**
     * Método que se ejecuta cuando se selecciona una fila de la tabla.
     *
     * @param evento Evento relacionado con la selección de la fila en la tabla.
     */
    public void filaSeleccionada(ActionEvent evento) {
        if (usuarioRolSeleccionado instanceof UsuarioRol) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón nuevo.
     *
     * @param evento Evento relacionado con el botón nuevo.
     */
    public void nuevo(ActionEvent evento) {

        this.usuarioRol = new UsuarioRol();
        this.usuario = new String();
        this.rol = new String();
        super.crear();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón modificar.
     *
     * @param evento Evento relacionado con el botón modificar.
     */
    public void modificar(ActionEvent evento) {
        try {
            this.respaldo = this.usuarioRolSeleccionado;
            this.usuarioRol = new UsuarioRol();
            BeanUtils.copyProperties(this.usuarioRol, this.usuarioRolSeleccionado);
            this.usuario = usuarioRol.getUsuario().getCodigo();
            this.rol = usuarioRol.getRol().getCodigo().toString();
            super.modificar();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón eliminar.
     *
     * @param evento Evento relacionado con el botón eliminar.
     */
    public void eliminar(ActionEvent evento) {
        this.usuarioRolServicio.eliminar(this.usuarioRol);
        this.usuarioRoles.remove(this.usuarioRolSeleccionado);
        MensajesGenericos.infoEliminar("UsuarioRol", this.usuarioRolSeleccionado.getPk().toString(), Boolean.TRUE);
        super.sinSeleccion();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón guardar.
     *
     * @param evento Evento relacionado con el botón guardar.
     */
    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                Usuario usuarioTmp = this.usuarioServicio.findByID(this.usuario);
                Rol rolTmp = this.rolServicio.findByID(Long.parseLong(this.rol));
                this.usuarioRol.getPk().setUsuario(this.usuario);
                this.usuarioRol.getPk().setRol(Long.parseLong(this.rol));
                this.usuarioRol.setUsuario(usuarioTmp);
                this.usuarioRol.setRol(rolTmp);
                this.usuarioRolServicio.crear(this.usuarioRol);
                this.usuarioRoles.add(this.usuarioRol);

                this.usuario = new String();
                this.rol = new String();
                MensajesGenericos.infoCrear("UsuarioRol", this.usuarioRol.getPk().toString().concat(" - ").concat(this.usuarioRol.getPk().toString()), Boolean.TRUE);
                super.sinSeleccion();
            } else {
                this.usuarioRolServicio.actualizar(this.usuarioRol);
                BeanUtils.copyProperties(this.respaldo, this.usuarioRol);
                MensajesGenericos.infoModificar("UsuarioRol", this.usuarioRol.getPk().toString().concat(" - ").concat(this.usuarioRol.getPk().toString()), Boolean.TRUE);
                super.seleccionadoUno();
                this.usuario = new String();
                this.rol = new String();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón cancelar.
     *
     * @param evento Evento relacionado con el botón cancelar.
     */
    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        MensajesGenericos.infoCancelar();
    }

    public List<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

    public UsuarioRol getUsuarioRolSeleccionado() {
        return usuarioRolSeleccionado;
    }

    public void setUsuarioRolSeleccionado(UsuarioRol usuarioRolSeleccionado) {
        this.usuarioRolSeleccionado = usuarioRolSeleccionado;
    }

    public UsuarioRol getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(UsuarioRol respaldo) {
        this.respaldo = respaldo;
    }

    public UsuarioRol getUsuarioRol() {
        return usuarioRol;
    }

    public void setUsuarioRol(UsuarioRol usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
