/*
 * KAYMANTA CIA. LTDA.
 * Sistema: SIGA 1.0 - KAYMANTA
 * Creado: 11-oct-2012 - 01:52:08
 * 
 * Los contenidos de este archivo son propiedad intelectual de KAYMANTA CIA. LTDA.
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de KAYMANTA CIA. LTDA.
 * 
 * Copyright 2012-2013 Kaymanta Todos los derechos reservados.
 */
package ec.kaymanta.gestproy.web.beans;

import ec.kaymanta.gestproy.modelo.Canton;
import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.servicio.CantonServicio;
import ec.kaymanta.gestproy.servicio.ProvinciaServicio;
import ec.kaymanta.gestproy.web.util.MensajesGenericos;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Clase que maneja la interfaz de usuario correspondiente a: Administración de
 * Cantones.
 *
 * @author Henry Coral
 */
@ManagedBean
@ViewScoped
public class CantonBean extends BotonesBean implements Serializable {

    private final String ENTIDAD = "Cantón";
    @EJB
    private CantonServicio cantonServicio;
    @EJB
    private ProvinciaServicio provinciaServicio;
    private List<Canton> cantons;
    private Canton cantonSeleccionado;
    private Canton respaldo;
    private Canton canton;
    private String provincia;
    private List<Provincia> provinciasB;

    /**
     * Método que se ejecuta inmediatamente después de la inicialización del
     * objeto. Se encarga de obtener el listado de todos los registros de la
     * entidad: Canton.
     */
    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.provinciasB = this.provinciaServicio.obtener();
    }

    public void cargarTabla(ActionEvent evento) {
        this.cantons = this.cantonServicio.obtenerPorProvincia(this.provincia);
    }

    /**
     * Método que se ejecuta cuando se selecciona una fila de la tabla.
     *
     * @param evento Evento relacionado con la selección de la fila en la tabla.
     */
    public void filaSeleccionada(ActionEvent evento) {
        if (cantonSeleccionado instanceof Canton) {
            super.seleccionadoUno();
        } else {
            super.sinSeleccion();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón nuevo.
     *
     * @param evento Evento relacionado con el botón nuevo.
     */
    public void nuevo(ActionEvent evento) {
        Provincia provinciaTmp = this.provinciaServicio.findByID(Long.parseLong(this.provincia));
        this.canton = new Canton();
        this.canton.getPk().setProvincia(Long.parseLong(this.provincia));
        this.canton.setProvincia(provinciaTmp);
        super.crear();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón modificar.
     *
     * @param evento Evento relacionado con el botón modificar.
     */
    public void modificar(ActionEvent evento) {
        try {
            this.respaldo = this.cantonSeleccionado;
            this.canton = new Canton();
            BeanUtils.copyProperties(this.canton, this.cantonSeleccionado);
            super.modificar();
        } catch (Exception e) {
            MensajesGenericos.errorCopyProperties();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón eliminar.
     *
     * @param evento Evento relacionado con el botón eliminar.
     */
    public void eliminar(ActionEvent evento) {
        this.cantonServicio.eliminar(this.canton.getPk());
        this.cantons.remove(this.cantonSeleccionado);
        MensajesGenericos.infoEliminar(ENTIDAD, this.cantonSeleccionado.getPk().toString(), Boolean.TRUE);
        super.sinSeleccion();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón guardar.
     *
     * @param evento Evento relacionado con el botón guardar.
     */
    public void guardar(ActionEvent evento) {
        try {
            if (super.getEnRegistro()) {
                this.canton.getPk().setCodigoCanton(Long.parseLong(String.valueOf(this.cantonServicio.obtenerPorProvincia(provincia).size())) + 1);
                this.cantonServicio.crear(this.canton);
                this.cantons.add(this.canton);
                MensajesGenericos.infoCrear(ENTIDAD, this.canton.getPk().toString().concat(" - ").concat(this.canton.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
            } else {
                this.cantonServicio.actualizar(this.canton);
                BeanUtils.copyProperties(this.respaldo, this.canton);
                MensajesGenericos.infoModificar(ENTIDAD, this.canton.getPk().toString().concat(" - ").concat(this.canton.getNombre()), Boolean.TRUE);
                super.seleccionadoUno();
            }
        } catch (Exception e) {
            MensajesGenericos.errorGuardar();
        }
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón cancelar.
     *
     * @param evento Evento relacionado con el botón cancelar.
     */
    public void cancelar(ActionEvent evento) {
        if (super.getEnRegistro()) {
            super.sinSeleccion();
        } else {
            super.seleccionadoUno();
        }
        MensajesGenericos.infoCancelar();
    }

    /**
     * Retorna la lista de objetos pertenecientes a la entidad: Canton.
     *
     * @return Lista de todos los registros de la entidad: Canton.
     */
    public List<Canton> getCantons() {
        return cantons;
    }

    /**
     * Retorna el objeto que se encuentra seleccionado.
     *
     * @return Objeto actualmente seleccionado.
     */
    public Canton getCantonSeleccionado() {
        return cantonSeleccionado;
    }

    /**
     * Asigna el grupo que se ha seleccionado.
     *
     * @param grupoSeleccionado Objeto seleccionado.
     */
    public void setCantonSeleccionado(Canton cantonSeleccionado) {
        this.cantonSeleccionado = cantonSeleccionado;
    }

    /**
     * Obtiene el objeto canton.
     *
     * @return objeto canton.
     */
    public Canton getCanton() {
        return canton;
    }

    /**
     * Asigna el objeto canton.
     *
     * @param canton Objeto a ser asignado.
     */
    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Provincia> getProvinciasB() {
        return provinciasB;
    }
}
