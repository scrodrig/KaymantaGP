/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.TipoGasto;
import ec.kaymanta.gestproy.servicio.TipoGastoServicio;
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
public class TiposGastoBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of TiposGastoBean
     */
    @EJB
    private TipoGastoServicio tipoGastoServicio;
    private List<TipoGasto> tipos;
    private TipoGasto tipoGasto;
    private TipoGasto tipoGastoSeleccionado;
    private TipoGasto respaldo;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.tipos = this.tipoGastoServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.tipoGasto = new TipoGasto();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        tipoGasto = new TipoGasto();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        tipoGasto = new TipoGasto();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.tipoGasto = new TipoGasto();
            this.tipoGasto = (TipoGasto) BeanUtils.cloneBean(this.tipoGastoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.tipoGastoServicio.crear(this.tipoGasto);
                this.tipos.add(this.tipoGasto);
                MensajesGenericos.infoCrear("TipoGasto", this.tipoGasto.getCodigo().toString().concat(" - ").concat(this.tipoGasto.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.tipos.indexOf(this.tipoGasto);
                this.tipoGastoServicio.actualizar(this.tipoGasto);
                tipos.set(i, this.tipoGasto);
                MensajesGenericos.infoModificar("TipoGasto", this.tipoGasto.getCodigo().toString().concat(" - ").concat(this.tipoGasto.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.tipoGasto = new TipoGasto();
        try {
            this.tipoGasto = (TipoGasto) BeanUtils.cloneBean(this.tipoGastoSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.tipoGastoSeleccionado);
        this.tipoGastoServicio.eliminar(this.tipoGastoSeleccionado);
        this.tipos.remove(this.tipoGastoSeleccionado);
        MensajesGenericos.infoEliminar("TipoGasto", this.tipoGasto.getCodigo().toString().concat(" - ").concat(this.tipoGasto.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (tipoGastoSeleccionado instanceof TipoGasto) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }
    
    //Getters and Setters
    public List<TipoGasto> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoGasto> tipos) {
        this.tipos = tipos;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public TipoGasto getTipoGastoSeleccionado() {
        return tipoGastoSeleccionado;
    }

    public void setTipoGastoSeleccionado(TipoGasto tipoGastoSeleccionado) {
        this.tipoGastoSeleccionado = tipoGastoSeleccionado;
    }

    public TipoGasto getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(TipoGasto respaldo) {
        this.respaldo = respaldo;
    }
    
    
}
