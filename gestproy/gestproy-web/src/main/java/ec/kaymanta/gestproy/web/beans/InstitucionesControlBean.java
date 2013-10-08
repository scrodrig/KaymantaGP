/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.InstitucionControl;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.InstitucionControlServicio;
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
public class InstitucionesControlBean extends BotonesBean implements Serializable{

    /**
     * Creates a new instance of InstitucionControlBean
     */
   /**
     * Creates a new instance of InstitucionControlBean
     */
   /**
     * Creates a new instance of UsuariosBean
     */
    @EJB
    private InstitucionControlServicio institucionControlServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    private List<InstitucionControl> instituciones;
    private InstitucionControl institucionControl;
    private InstitucionControl institucionSeleccionado;
    private InstitucionControl respaldo;
    private List<Usuario> usuarios;
    private Usuario usrSesion;
    private Usuario usrAuditoria;
    private String codigoUsuario;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.usuarios = this.usuarioServicio.obtenerUsuarios();
        this.instituciones = this.institucionControlServicio.obtener();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.institucionControl = new InstitucionControl();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        institucionControl = new InstitucionControl();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        institucionControl = new InstitucionControl();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.institucionControl = new InstitucionControl();
            this.institucionControl = (InstitucionControl) BeanUtils.cloneBean(this.institucionSeleccionado);
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
                this.institucionControl.setUsrCreacion(usrSesion.getCodigo());
                this.institucionControl.setFcreacion(new Date());
                this.institucionControlServicio.crear(this.institucionControl);
                this.instituciones.add(this.institucionControl);
                MensajesGenericos.infoCrear("InstitucionControl", this.institucionControl.getCodigo().toString().concat(" - ").concat(this.institucionControl.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.instituciones.indexOf(this.institucionControl);
                this.institucionControlServicio.actualizar(this.institucionControl);
                instituciones.set(i, this.institucionControl);
                MensajesGenericos.infoModificar("Usuario", this.institucionControl.getCodigo().toString().concat(" - ").concat(this.institucionControl.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.institucionControl = new InstitucionControl();
        try {
            this.institucionControl = (InstitucionControl) BeanUtils.cloneBean(this.institucionSeleccionado);
            //Invariable Objetos de Auditoria            
            this.institucionControl.setUsrModificacion(usrSesion.getCodigo());
            this.institucionControl.setFmodificacion(new Date());
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.institucionSeleccionado);
        this.institucionControlServicio.eliminar(this.institucionSeleccionado);        
        //this.empresaServicio.actualizar(empresaSeleccionado);
        this.instituciones.remove(this.institucionSeleccionado);
        MensajesGenericos.infoEliminar("InstitucionControl", this.institucionControl.getCodigo().toString().concat(" - ").concat(this.institucionControl.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (institucionSeleccionado instanceof InstitucionControl) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public List<InstitucionControl> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<InstitucionControl> instituciones) {
        this.instituciones = instituciones;
    }

    public InstitucionControl getInstitucionControl() {
        return institucionControl;
    }

    public void setInstitucionControl(InstitucionControl institucionControl) {
        this.institucionControl = institucionControl;
    }

    public InstitucionControl getInstitucionSeleccionado() {
        return institucionSeleccionado;
    }

    public void setInstitucionSeleccionado(InstitucionControl institucionSeleccionado) {
        this.institucionSeleccionado = institucionSeleccionado;
    }

    public InstitucionControl getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(InstitucionControl respaldo) {
        this.respaldo = respaldo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
    
    
}

