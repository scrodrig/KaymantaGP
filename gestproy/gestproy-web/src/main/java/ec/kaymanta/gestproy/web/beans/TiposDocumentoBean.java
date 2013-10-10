/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.TipoDocumento;
import ec.kaymanta.gestproy.servicio.TipoDocumentoServicio;
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
public class TiposDocumentoBean extends BotonesBean implements Serializable{

    /**
     * Creates a new instance of TiposGastoBean
     */
    @EJB
    private TipoDocumentoServicio tipoDocumentoServicio;
    private List<TipoDocumento> tipos;
    private TipoDocumento tipoDocumento;
    private TipoDocumento tipoDocumentoSeleccionado;
    private TipoDocumento respaldo;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.tipos = this.tipoDocumentoServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.tipoDocumento = new TipoDocumento();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        tipoDocumento = new TipoDocumento();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        tipoDocumento = new TipoDocumento();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.tipoDocumento = new TipoDocumento();
            this.tipoDocumento = (TipoDocumento) BeanUtils.cloneBean(this.tipoDocumentoSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.tipoDocumentoServicio.crear(this.tipoDocumento);
                this.tipos.add(this.tipoDocumento);
                MensajesGenericos.infoCrear("TipoDocumento", this.tipoDocumento.getCodigo().toString().concat(" - ").concat(this.tipoDocumento.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.tipos.indexOf(this.tipoDocumento);
                this.tipoDocumentoServicio.actualizar(this.tipoDocumento);
                tipos.set(i, this.tipoDocumento);
                MensajesGenericos.infoModificar("TipoDocumento", this.tipoDocumento.getCodigo().toString().concat(" - ").concat(this.tipoDocumento.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.tipoDocumento = new TipoDocumento();
        try {
            this.tipoDocumento = (TipoDocumento) BeanUtils.cloneBean(this.tipoDocumentoSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.tipoDocumentoSeleccionado);
        this.tipoDocumentoServicio.eliminar(this.tipoDocumentoSeleccionado);
        this.tipos.remove(this.tipoDocumentoSeleccionado);
        MensajesGenericos.infoEliminar("TipoDocumento", this.tipoDocumento.getCodigo().toString().concat(" - ").concat(this.tipoDocumento.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (tipoDocumentoSeleccionado instanceof TipoDocumento) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }
    
    //Getter and Setters
    public List<TipoDocumento> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoDocumento> tipos) {
        this.tipos = tipos;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoDocumento getTipoDocumentoSeleccionado() {
        return tipoDocumentoSeleccionado;
    }

    public void setTipoDocumentoSeleccionado(TipoDocumento tipoDocumentoSeleccionado) {
        this.tipoDocumentoSeleccionado = tipoDocumentoSeleccionado;
    }

    public TipoDocumento getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(TipoDocumento respaldo) {
        this.respaldo = respaldo;
    }
    
    
    
}
