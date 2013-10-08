/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.servicio.ProvinciaServicio;
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
public class ProvinciasBean extends BotonesBean implements Serializable{

    /**
     * Creates a new instance of ProvinciasBean
     */
     @EJB
    private ProvinciaServicio provinciaServicio;

    private List<Provincia> provincias;
    private Provincia provincia;
    private Provincia provinciaSeleccionado;
    private Provincia respaldo;


    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.provincias = this.provinciaServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.provincia = new Provincia();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        provincia = new Provincia();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        provincia = new Provincia();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.provincia = new Provincia();
            this.provincia = (Provincia) BeanUtils.cloneBean(this.provinciaSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }


    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.provinciaServicio.crear(this.provincia);
                this.provincias.add(this.provincia);
                MensajesGenericos.infoCrear("Provincia", this.provincia.getCodigo().toString().concat(" - ").concat(this.provincia.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.provincias.indexOf(this.provincia);
                this.provinciaServicio.actualizar(this.provincia);
                provincias.set(i, this.provincia);
                MensajesGenericos.infoModificar("Provincia", this.provincia.getCodigo().toString().concat(" - ").concat(this.provincia.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.provincia = new Provincia();
        try {
            this.provincia = (Provincia) BeanUtils.cloneBean(this.provinciaSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.provinciaSeleccionado);
        this.provinciaServicio.eliminar(this.provinciaSeleccionado);        
        this.provincias.remove(this.provinciaSeleccionado);
        MensajesGenericos.infoEliminar("Provincia", this.provincia.getCodigo().toString().concat(" - ").concat(this.provincia.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (provinciaSeleccionado instanceof Provincia) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Provincia getProvinciaSeleccionado() {
        return provinciaSeleccionado;
    }

    public void setProvinciaSeleccionado(Provincia provinciaSeleccionado) {
        this.provinciaSeleccionado = provinciaSeleccionado;
    }

    public Provincia getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Provincia respaldo) {
        this.respaldo = respaldo;
    }
    
    
}
