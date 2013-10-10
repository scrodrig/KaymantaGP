/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.TipoEntregable;
import ec.kaymanta.gestproy.servicio.TipoEntregableServicio;
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
public class TiposEntregableBean extends BotonesBean implements Serializable{

     @EJB
    private TipoEntregableServicio tipoEntregableServicio;
    private List<TipoEntregable> tipos;
    private TipoEntregable tipoEntregable;
    private TipoEntregable tipoEntregableSeleccionado;
    private TipoEntregable respaldo;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.tipos = this.tipoEntregableServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.tipoEntregable = new TipoEntregable();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        tipoEntregable = new TipoEntregable();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        tipoEntregable = new TipoEntregable();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.tipoEntregable = new TipoEntregable();
            this.tipoEntregable = (TipoEntregable) BeanUtils.cloneBean(this.tipoEntregableSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.tipoEntregableServicio.crear(this.tipoEntregable);
                this.tipos.add(this.tipoEntregable);
                MensajesGenericos.infoCrear("TipoEntregable", this.tipoEntregable.getCodigo().toString().concat(" - ").concat(this.tipoEntregable.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.tipos.indexOf(this.tipoEntregable);
                this.tipoEntregableServicio.actualizar(this.tipoEntregable);
                tipos.set(i, this.tipoEntregable);
                MensajesGenericos.infoModificar("TipoEntregable", this.tipoEntregable.getCodigo().toString().concat(" - ").concat(this.tipoEntregable.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.tipoEntregable = new TipoEntregable();
        try {
            this.tipoEntregable = (TipoEntregable) BeanUtils.cloneBean(this.tipoEntregableSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.tipoEntregableSeleccionado);
        this.tipoEntregableServicio.eliminar(this.tipoEntregableSeleccionado);
        this.tipos.remove(this.tipoEntregableSeleccionado);
        MensajesGenericos.infoEliminar("TipoEntregable", this.tipoEntregable.getCodigo().toString().concat(" - ").concat(this.tipoEntregable.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (tipoEntregableSeleccionado instanceof TipoEntregable) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }
 
    //Getter and Setters
    public List<TipoEntregable> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoEntregable> tipos) {
        this.tipos = tipos;
    }

    public TipoEntregable getTipoEntregable() {
        return tipoEntregable;
    }

    public void setTipoEntregable(TipoEntregable tipoEntregable) {
        this.tipoEntregable = tipoEntregable;
    }

    public TipoEntregable getTipoEntregableSeleccionado() {
        return tipoEntregableSeleccionado;
    }

    public void setTipoEntregableSeleccionado(TipoEntregable tipoEntregableSeleccionado) {
        this.tipoEntregableSeleccionado = tipoEntregableSeleccionado;
    }

    public TipoEntregable getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(TipoEntregable respaldo) {
        this.respaldo = respaldo;
    }
    
    
}
