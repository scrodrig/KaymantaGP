/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empresa;
import ec.kaymanta.gestproy.modelo.Interesado;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
import ec.kaymanta.gestproy.servicio.InteresadoServicio;
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
public class InteresadosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of InteresadoBean
     */
    @EJB
    private InteresadoServicio interesadoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private EmpresaServicio empresaServicio;
    private List<Interesado> interesados;
    private Interesado interesado;
    private Interesado interesadoSeleccionado;
    private Interesado respaldo;
    private List<Usuario> usuarios;
    private Usuario usrSesion;
    private Usuario usrAuditoria;
    private String codigoUsuario;
    //private String codigoUsuario;
    private List<Empresa> empresas;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.usuarios = this.usuarioServicio.obtenerUsuarios();
        this.interesados = this.interesadoServicio.obtener();
        this.empresas = this.empresaServicio.obtener();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.interesado = new Interesado();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        interesado = new Interesado();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        interesado = new Interesado();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.interesado = new Interesado();
            this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getUsrAuditoria(String usr) {
        if (usr == null || "".equals(usr)) {
            return "";
        } else {
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
                this.interesado.setUsrCreacion(usrSesion.getCodigo());
                this.interesado.setFcreacion(new Date());
                this.interesado.setEmpresa(this.empresaServicio.findByID(interesado.getCodEmpresa()));
                this.interesadoServicio.crear(this.interesado);
                this.interesados.add(this.interesado);
                MensajesGenericos.infoCrear("Interesado", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.interesados.indexOf(this.interesado);
                this.interesadoServicio.actualizar(this.interesado);
                interesados.set(i, this.interesado);
                MensajesGenericos.infoModificar("Usuario", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.interesado = new Interesado();
        try {
            this.interesado = (Interesado) BeanUtils.cloneBean(this.interesadoSeleccionado);
            //Invariable Objetos de Auditoria            
            this.interesado.setUsrModificacion(usrSesion.getCodigo());
            this.interesado.setFmodificacion(new Date());
            this.interesado.setEmpresa(this.empresaServicio.findByID(interesado.getCodEmpresa()));
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        this.interesadoServicio.eliminar(this.interesadoSeleccionado);
        this.interesados.remove(this.interesadoSeleccionado);
        MensajesGenericos.infoEliminar("Interesado", this.interesado.getCodigo().toString().concat(" - ").concat(this.interesado.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (interesadoSeleccionado instanceof Interesado) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    //Getters and Setters
    public List<Interesado> getInteresados() {
        return interesados;
    }

    public void setInteresados(List<Interesado> interesados) {
        this.interesados = interesados;
    }

    public Interesado getInteresado() {
        return interesado;
    }

    public void setInteresado(Interesado interesado) {
        this.interesado = interesado;
    }

    public Interesado getInteresadoSeleccionado() {
        return interesadoSeleccionado;
    }

    public void setInteresadoSeleccionado(Interesado interesadoSeleccionado) {
        this.interesadoSeleccionado = interesadoSeleccionado;
    }

    public Interesado getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Interesado respaldo) {
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

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
