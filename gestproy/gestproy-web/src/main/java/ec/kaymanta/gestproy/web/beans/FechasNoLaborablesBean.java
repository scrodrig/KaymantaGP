/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.FechasNoLaborables;
import ec.kaymanta.gestproy.servicio.FechasNoLaborablesServicio;
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
public class FechasNoLaborablesBean extends BotonesBean implements Serializable {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     * Creates a new instance of ModulosBean
     */
    @EJB
    private FechasNoLaborablesServicio fechasNoLaborablesServicio;
    private List<FechasNoLaborables> fechasNoLaborables;
    private FechasNoLaborables fechaNoLaborable;
    private FechasNoLaborables fechaNoLaborableSeleccionada;
    private FechasNoLaborables respaldo;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.fechasNoLaborables = this.fechasNoLaborablesServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.fechaNoLaborable = new FechasNoLaborables();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        fechaNoLaborable = new FechasNoLaborables();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        fechaNoLaborable = new FechasNoLaborables();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.fechaNoLaborable = new FechasNoLaborables();
            this.fechaNoLaborable = (FechasNoLaborables) BeanUtils.cloneBean(this.fechaNoLaborableSeleccionada);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.fechasNoLaborablesServicio.crear(this.fechaNoLaborable);
                this.fechasNoLaborables.add(this.fechaNoLaborable);
                MensajesGenericos.infoCrear("FechasNoLaborables", this.fechaNoLaborable.getFecha().toString(), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.fechasNoLaborables.indexOf(this.respaldo);
                this.fechasNoLaborablesServicio.eliminar(this.respaldo);
                this.fechasNoLaborablesServicio.crear(this.fechaNoLaborable);
                fechasNoLaborables.set(i, this.fechaNoLaborable);
                MensajesGenericos.infoModificar("FechasNoLaborables", this.fechaNoLaborable.getFecha().toString(), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            super.sinSeleccion();
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.fechaNoLaborable = new FechasNoLaborables();
        try {
            this.respaldo = (FechasNoLaborables) BeanUtils.cloneBean(this.fechaNoLaborableSeleccionada);
            this.fechaNoLaborable = (FechasNoLaborables) BeanUtils.cloneBean(this.fechaNoLaborableSeleccionada);
            super.modificar();
        } catch (Exception ex) {
            super.sinSeleccion();
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        try {
            this.fechasNoLaborablesServicio.eliminar(this.fechaNoLaborableSeleccionada);
            this.fechasNoLaborables.remove(this.fechaNoLaborableSeleccionada);
            MensajesGenericos.infoEliminar("FechasNoLaborables", this.fechaNoLaborable.getFecha().toString(), Boolean.TRUE);
            super.sinSeleccion();
        } catch (Exception e) {
            super.sinSeleccion();
            MensajesGenericos.infoError("Error eliminando el registro");
        }
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (fechaNoLaborableSeleccionada instanceof FechasNoLaborables) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public List<FechasNoLaborables> getFechasNoLaborables() {
        return fechasNoLaborables;
    }

    public void setFechasNoLaborables(List<FechasNoLaborables> fechasNoLaborables) {
        this.fechasNoLaborables = fechasNoLaborables;
    }

    public FechasNoLaborables getFechaNoLaborable() {
        return fechaNoLaborable;
    }

    public void setFechaNoLaborable(FechasNoLaborables fechaNoLaborable) {
        this.fechaNoLaborable = fechaNoLaborable;
    }

    public FechasNoLaborables getFechaNoLaborableSeleccionada() {
        return fechaNoLaborableSeleccionada;
    }

    public void setFechaNoLaborableSeleccionada(FechasNoLaborables fechaNoLaborableSeleccionada) {
        this.fechaNoLaborableSeleccionada = fechaNoLaborableSeleccionada;
    }

    public FechasNoLaborables getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(FechasNoLaborables respaldo) {
        this.respaldo = respaldo;
    }
}
