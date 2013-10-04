/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.servicio.ModuloServicio;
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
public class ModulosBean extends BotonesBean implements Serializable{

    /**
     * Creates a new instance of ModulosBean
     */
    @EJB
    private ModuloServicio moduloServicio;

    private List<Modulo> modulos;
    private Modulo modulo;
    private Modulo moduloSeleccionado;
    private Modulo respaldo;


    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.modulos = this.moduloServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.modulo = new Modulo();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        modulo = new Modulo();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        modulo = new Modulo();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.modulo = new Modulo();
            this.modulo = (Modulo) BeanUtils.cloneBean(this.moduloSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }


    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.moduloServicio.crear(this.modulo);
                this.modulos.add(this.modulo);
                MensajesGenericos.infoCrear("Modulo", this.modulo.getCodigo().toString().concat(" - ").concat(this.modulo.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.modulos.indexOf(this.modulo);
                this.moduloServicio.actualizar(this.modulo);
                modulos.set(i, this.modulo);
                MensajesGenericos.infoModificar("Modulo", this.modulo.getCodigo().toString().concat(" - ").concat(this.modulo.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.modulo = new Modulo();
        try {
            this.modulo = (Modulo) BeanUtils.cloneBean(this.moduloSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.moduloSeleccionado);
        this.moduloServicio.eliminar(this.moduloSeleccionado);        
        this.modulos.remove(this.moduloSeleccionado);
        MensajesGenericos.infoEliminar("Modulo", this.modulo.getCodigo().toString().concat(" - ").concat(this.modulo.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (moduloSeleccionado instanceof Modulo) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    //Getters and Setters
    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Modulo getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(Modulo moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }

    public Modulo getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Modulo respaldo) {
        this.respaldo = respaldo;
    }
    
    
}
