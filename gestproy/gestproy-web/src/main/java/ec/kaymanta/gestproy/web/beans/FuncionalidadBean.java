/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Funcionalidad;
import ec.kaymanta.gestproy.modelo.Modulo;
import ec.kaymanta.gestproy.servicio.FuncionalidadServicio;
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
public class FuncionalidadBean extends BotonesBean implements Serializable {

    /**
     * Creates a new instance of ModulosBean
     */
    @EJB
    private FuncionalidadServicio funcionalidadServicio;
    @EJB
    private ModuloServicio moduloServicio;
    private List<Funcionalidad> funcionalidades;
    private Funcionalidad funcionalidad;
    private Funcionalidad funcionalidadSeleccionado;
    private Funcionalidad respaldo;
    private String codigoModulo;
    private List<Modulo> modulos;
    private Modulo modulo;

    /**
     * PostConstructor of the function, it launchs after the constructor
     */
    @PostConstruct
    @Override
    public void postConstructor() {

        super.sinSeleccion();
        this.funcionalidades = this.funcionalidadServicio.obtener();
        this.modulos = this.moduloServicio.obtener();

    }

    public void nuevo(ActionEvent evento) {
        super.crear();
        this.funcionalidad = new Funcionalidad();
        this.modulo = new Modulo();
    }

    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        funcionalidad = new Funcionalidad();
        MensajesGenericos.infoCancelar();
    }

    public void volver(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        funcionalidad = new Funcionalidad();
    }

    public void verAuditoria(ActionEvent evento) throws IllegalAccessException {
        try {
            this.funcionalidad = new Funcionalidad();
            this.funcionalidad = (Funcionalidad) BeanUtils.cloneBean(this.funcionalidadSeleccionado);
            super.verAuditoria();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }

    }

    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.modulo = new Modulo();
                this.modulo = moduloServicio.findByID(Long.parseLong(codigoModulo));
                System.out.println(modulo.getNombre());

                this.funcionalidad.setModulo(modulo);
                this.funcionalidad.setCodModulo(modulo.getCodigo());
                
                this.funcionalidadServicio.crear(this.funcionalidad);
                this.funcionalidades.add(this.funcionalidad);
                MensajesGenericos.infoCrear("Funcionalidad", this.funcionalidad.getCodigo().toString().concat(" - ").concat(this.funcionalidad.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else if (super.getEnEdicion()) {
                int i = this.funcionalidades.indexOf(this.funcionalidad);
                this.funcionalidad.setModulo(this.moduloServicio.findByID(Long.parseLong(codigoModulo)));
                this.funcionalidadServicio.actualizar(this.funcionalidad);
                funcionalidades.set(i, this.funcionalidad);
                MensajesGenericos.infoModificar("Funcionalidad", this.funcionalidad.getCodigo().toString().concat(" - ").concat(this.funcionalidad.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }

    }

    public void modificar(ActionEvent evento) {
        this.funcionalidad = new Funcionalidad();
        try {
            this.funcionalidad = (Funcionalidad) BeanUtils.cloneBean(this.funcionalidadSeleccionado);
            //Invariable Objetos de Auditoria            
            super.modificar();
        } catch (Exception ex) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    public void eliminar(ActionEvent evento) {
        System.out.println(this.funcionalidadSeleccionado);
        this.funcionalidadServicio.eliminar(this.funcionalidadSeleccionado);
        this.funcionalidades.remove(this.funcionalidadSeleccionado);
        MensajesGenericos.infoEliminar("Funcionalidad", this.funcionalidad.getCodigo().toString().concat(" - ").concat(this.funcionalidad.getNombre()), Boolean.TRUE);
        super.sinSeleccion();
    }

    public void filaSeleccionada(ActionEvent evento) {
        if (funcionalidadSeleccionado instanceof Funcionalidad) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    //Getters And Setters
    public List<Funcionalidad> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public Funcionalidad getFuncionalidad() {
        return funcionalidad;
    }

    public void setFuncionalidad(Funcionalidad funcionalidad) {
        this.funcionalidad = funcionalidad;
    }

    public Funcionalidad getFuncionalidadSeleccionado() {
        return funcionalidadSeleccionado;
    }

    public void setFuncionalidadSeleccionado(Funcionalidad funcionalidadSeleccionado) {
        this.funcionalidadSeleccionado = funcionalidadSeleccionado;
    }

    public Funcionalidad getRespaldo() {
        return respaldo;
    }

    public void setRespaldo(Funcionalidad respaldo) {
        this.respaldo = respaldo;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public void setCodigoModulo(String codigoModulo) {
        this.codigoModulo = codigoModulo;
    }

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
}
