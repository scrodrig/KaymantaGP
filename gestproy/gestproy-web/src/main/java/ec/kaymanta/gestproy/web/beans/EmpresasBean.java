/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empresa;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.EmpresaServicio;
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
public class EmpresasBean extends BotonesBean implements Serializable{

    /**
     * Creates a new instance of EmpresaBean
     */
   /**
     * Creates a new instance of UsuariosBean
     */
    @EJB
    private EmpresaServicio empresaServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    private List<Empresa> empresas;
    private Empresa empresa;
    private Empresa empresaSeleccionado;
    private Empresa respaldo;
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
        this.empresas = this.empresaServicio.obtener();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.empresa = new Empresa();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        empresa = new Empresa();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        empresa = new Empresa();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.empresa = new Empresa();
            this.empresa = (Empresa) BeanUtils.cloneBean(this.empresaSeleccionado);
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
                this.empresa.setUsrCreacion(usrSesion.getCodigo());
                this.empresa.setFcreacion(new Date());
                this.empresaServicio.crear(this.empresa);
                this.empresas.add(this.empresa);
                MensajesGenericos.infoCrear("Empresa", this.empresa.getCodigo().toString().concat(" - ").concat(this.empresa.getRazonSocial()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.empresas.indexOf(this.empresa);
                this.empresaServicio.actualizar(this.empresa);
                empresas.set(i, this.empresa);
                MensajesGenericos.infoModificar("Empresa", this.empresa.getCodigo().toString().concat(" - ").concat(this.empresa.getRazonSocial()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.empresa = new Empresa();
        try {
            this.empresa = (Empresa) BeanUtils.cloneBean(this.empresaSeleccionado);
            //Invariable Objetos de Auditoria            
            this.empresa.setUsrModificacion(usrSesion.getCodigo());
            this.empresa.setFmodificacion(new Date());
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.empresaSeleccionado);
        this.empresaServicio.eliminar(this.empresaSeleccionado);        
        //this.empresaServicio.actualizar(empresaSeleccionado);
        this.empresas.remove(this.empresaSeleccionado);
        MensajesGenericos.infoEliminar("Empresa", this.empresa.getCodigo().toString().concat(" - ").concat(this.empresa.getRazonSocial()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (empresaSeleccionado instanceof Empresa) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }
    
    //Getters And Setters
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresaSeleccionado() {
        return empresaSeleccionado;
    }

    public void setEmpresaSeleccionado(Empresa empresaSeleccionado) {
        this.empresaSeleccionado = empresaSeleccionado;
    }

    public Empresa getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Empresa respaldo) {
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
