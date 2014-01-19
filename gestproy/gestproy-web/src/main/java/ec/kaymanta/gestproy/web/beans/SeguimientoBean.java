/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.ActividadSegumiento;
import ec.kaymanta.gestproy.modelo.Empleado;
import ec.kaymanta.gestproy.modelo.Usuario;
import ec.kaymanta.gestproy.servicio.ActividadSeguimientoServicio;
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
public class SeguimientoBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of SeguimientoBean
     */
    @EJB
    private ActividadSeguimientoServicio actividadSeguimientoServicio;
    @EJB
    private UsuarioServicio usuarioServicio;
    @EJB
    private EmpleadoServicio empleadoServicio;    
    private ActividadSegumiento actividadSeguimiento;
    private List<ActividadSegumiento> actividadesSeguimiento;
    private ActividadSegumiento actividadSeguimientoSeleccionado;
    private Usuario usrSesion;
    
    @PostConstruct
    @Override
    public void postConstructor() {
        
        super.sinSeleccion();
        this.usrSesion = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        this.actividadesSeguimiento=this.actividadSeguimientoServicio.findBySupervisor(usrSesion.getCodigo());
    }
    
    public void revisar(ActionEvent evento)
    {
        super.crear();        
    }
    
    public Empleado getEmpleado(String codigo)
    {
         return this.empleadoServicio.findByID(codigo);
    }
    
    public void filaSeleccionada(ActionEvent evento) {
        if (actividadSeguimientoSeleccionado instanceof ActividadSegumiento) {
            super.seleccionadoUno();
            try {
                this.actividadSeguimiento = (ActividadSegumiento) BeanUtils.cloneBean(this.actividadSeguimientoSeleccionado);
            } catch (Exception e) {
            }
        } else {
            super.sinSeleccion();
        }
    }
    
    public void guardar(ActionEvent evento)
    {
        this.actividadSeguimiento.setUsrModificacion(usrSesion.getCodigo());
        this.actividadSeguimiento.setFmodificacion(new Date());
        this.actividadSeguimientoServicio.actualizar(actividadSeguimiento);
        super.sinSeleccion();
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
    
    public String getEstado(String estado) {
        if (estado == null || "".equals(estado)) {
            return "";
        } else {
            if (estado.equals("P")) {
                return "En Proceso";
            } else if (estado.equals("R")) {
                return "Revisado";
            }
            return "Desconocido";
        }
    }
    
    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.actividadSeguimiento = new ActividadSegumiento();
            this.actividadSeguimiento = (ActividadSegumiento) BeanUtils.cloneBean(this.actividadSeguimientoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
        
    }

    public ActividadSegumiento getActividadSeguimiento() {
        return actividadSeguimiento;
    }

    public void setActividadSeguimiento(ActividadSegumiento actividadSeguimiento) {
        this.actividadSeguimiento = actividadSeguimiento;
    }

    public List<ActividadSegumiento> getActividadesSeguimiento() {
        return actividadesSeguimiento;
    }

    public void setActividadesSeguimiento(List<ActividadSegumiento> actividadesSeguimiento) {
        this.actividadesSeguimiento = actividadesSeguimiento;
    }

    public ActividadSegumiento getActividadSeguimientoSeleccionado() {
        return actividadSeguimientoSeleccionado;
    }

    public void setActividadSeguimientoSeleccionado(ActividadSegumiento actividadSeguimientoSeleccionado) {
        this.actividadSeguimientoSeleccionado = actividadSeguimientoSeleccionado;
    }
    
    
    
    
}
