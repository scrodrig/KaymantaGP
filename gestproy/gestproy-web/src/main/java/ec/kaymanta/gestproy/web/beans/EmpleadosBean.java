/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.EmpleadoServicio;
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
public class EmpleadosBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of UsuariosBean
     */
    @EJB
    private EmpleadoServicio empleadoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    private List<Empleado> empleados;
    private Empleado empleado;
    private Empleado empleadoSeleccionado;
    private Empleado respaldo;
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
        this.empleados = this.empleadoServicio.obtener();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.empleado = new Empleado();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        empleado = new Empleado();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        empleado = new Empleado();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.empleado = new Empleado();
            this.empleado = (Empleado) BeanUtils.cloneBean(this.empleadoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public String getNombreEstado(String estado)
    {
        if(estado.equals("A") || estado.equals("a"))
            return "Activo";
        else
            return "Inactivo";
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
                this.empleado.setCodigo(codigoUsuario);
                this.empleado.setEstado("A");
                this.empleado.setUsrCreacion(usrSesion.getCodigo());
                this.empleado.setFcreacion(new Date());
                this.empleadoServicio.crear(this.empleado);
                this.empleados.add(this.empleado);
                MensajesGenericos.infoCrear("Empleado", this.empleado.getCodigo().toString().concat(" - ").concat(this.empleado.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.empleados.indexOf(this.empleado);
                this.empleadoServicio.actualizar(this.empleado);
                empleados.set(i, this.empleado);
                MensajesGenericos.infoModificar("Usuario", this.empleado.getCodigo().toString().concat(" - ").concat(this.empleado.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.empleado = new Empleado();
        try {
            this.empleado = (Empleado) BeanUtils.cloneBean(this.empleadoSeleccionado);
            //Invariable Objetos de Auditoria            
            this.empleado.setUsrModificacion(usrSesion.getCodigo());
            this.empleado.setFmodificacion(new Date());
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        //this.empleadoServicio.eliminar(this.empleadoSeleccionado);
        this.empleadoSeleccionado.setEstado("I");
        this.empleadoServicio.actualizar(empleadoSeleccionado);
        //this.empleados.remove(this.empleadoSeleccionado);
        MensajesGenericos.infoEliminar("Empleado", this.empleado.getCodigo().toString().concat(" - ").concat(this.empleado.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (empleadoSeleccionado instanceof Empleado) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public Empleado getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Empleado respaldo) {
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
