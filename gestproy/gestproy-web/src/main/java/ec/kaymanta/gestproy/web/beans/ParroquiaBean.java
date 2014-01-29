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
import ec.kaymanta.gestproy.modelo.CantonPK;
import ec.kaymanta.gestproy.modelo.Parroquia;
import ec.kaymanta.gestproy.modelo.ParroquiaPK;
import ec.kaymanta.gestproy.modelo.Provincia;
import ec.kaymanta.gestproy.servicio.CantonServicio;
import ec.kaymanta.gestproy.servicio.ParroquiaServicio;
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
 * Parroquias.
 *
 * @author Henry Coral
 */
@ManagedBean
@ViewScoped
public class ParroquiaBean extends BotonesBean implements Serializable {

    private final String ENTIDAD = "Parroquias";
    @EJB
    private ParroquiaServicio parroquiaServicio;
    @EJB
    private ProvinciaServicio provinciaServicio;
    @EJB
    private CantonServicio cantonServicio;
    private List<Parroquia> parroquias;
    private Parroquia parroquiaSeleccionado;
    private Parroquia respaldo;
    private Parroquia parroquia;
    private String provincia;
    private String canton;
    private List<Provincia> provinciasB;
    private List<Canton> cantonesB;

    /**
     * Método que se ejecuta inmediatamente después de la inicialización del
     * objeto. Se encarga de obtener el listado de todos los registros de la
     * entidad: Parroquia.
     */
    @PostConstruct
    @Override
    public void postConstructor() {
        super.sinSeleccion();
        this.provinciasB = this.provinciaServicio.obtener();
    }

    public void actualizaCantonesB(ActionEvent evento) {
        this.cantonesB = this.cantonServicio.obtenerPorProvincia(this.provincia);
    }

    public void cargarTabla(ActionEvent evento) {
        this.parroquias = this.parroquiaServicio.obtenerPorProvinciaCanton(this.provincia, this.canton);
    }

    /**
     * Método que se ejecuta cuando se selecciona una fila de la tabla.
     *
     * @param evento Evento relacionado con la selección de la fila en la tabla.
     */
    public void filaSeleccionada(ActionEvent evento) {
        if (parroquiaSeleccionado instanceof Parroquia) {
            try {
                this.parroquia = new Parroquia();
                this.parroquia = (Parroquia) BeanUtils.cloneBean(this.parroquiaSeleccionado);
            } catch (Exception e) {
            }
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
        CantonPK cantonPK = new CantonPK(Long.parseLong(this.canton), Long.parseLong(this.provincia));
        Canton cantonTmp = this.cantonServicio.obtenerPorId(cantonPK);
        this.parroquia = new Parroquia();
        ParroquiaPK parroquiaPK = new ParroquiaPK();
        parroquiaPK.setCanton(Long.parseLong(this.canton));
        parroquiaPK.setProvincia(Long.parseLong(this.provincia));
        this.parroquia.setPk(parroquiaPK);
        this.parroquia.setCanton(cantonTmp);
        super.crear();
    }

    /**
     * Método que se ejecuta cuando se da clic en el botón modificar.
     *
     * @param evento Evento relacionado con el botón modificar.
     */
    public void modificar(ActionEvent evento) {
        try {
            this.respaldo = this.parroquiaSeleccionado;
            this.parroquia = new Parroquia();
            BeanUtils.copyProperties(this.parroquia, this.parroquiaSeleccionado);
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
        this.parroquiaServicio.eliminar(this.parroquia.getPk());
        this.parroquias.remove(this.parroquiaSeleccionado);
        MensajesGenericos.infoEliminar(ENTIDAD, this.parroquiaSeleccionado.getPk().toString(), Boolean.TRUE);
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
                this.parroquia.getPk().setCodigoParroquia(Long.parseLong(String.valueOf(this.parroquiaServicio.obtenerPorProvinciaCanton(provincia, canton).size()))+1);                
                this.parroquiaServicio.crear(this.parroquia);
                this.parroquias.add(this.parroquia);
                
                MensajesGenericos.infoCrear(ENTIDAD, this.parroquia.getPk().toString().concat(" - ").concat(this.parroquia.getNombre()), Boolean.TRUE);
                super.sinSeleccion();
                //this.canton = new String();
                //this.provincia = new String();
            } else {
                this.parroquiaServicio.actualizar(this.parroquia);
                BeanUtils.copyProperties(this.respaldo, this.parroquia);
                MensajesGenericos.infoModificar(ENTIDAD, this.parroquia.getPk().toString().concat(" - ").concat(this.parroquia.getNombre()), Boolean.TRUE);
                super.seleccionadoUno();
                //this.canton = new String();
                //this.provincia = new String();
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
        this.canton = new String();
        this.provincia = new String();
        MensajesGenericos.infoCancelar();
    }

    /**
     * Retorna la lista de objetos pertenecientes a la entidad: Parroquia.
     *
     * @return Lista de todos los registros de la entidad: Parroquia.
     */
    public List<Parroquia> getParroquias() {
        return parroquias;
    }

    /**
     * Retorna el objeto que se encuentra seleccionado.
     *
     * @return Objeto actualmente seleccionado.
     */
    public Parroquia getParroquiaSeleccionado() {
        return parroquiaSeleccionado;
    }

    /**
     * Asigna el grupo que se ha seleccionado.
     *
     * @param grupoSeleccionado Objeto seleccionado.
     */
    public void setParroquiaSeleccionado(Parroquia parroquiaSeleccionado) {
        this.parroquiaSeleccionado = parroquiaSeleccionado;
    }

    /**
     * Obtiene el objeto parroquia.
     *
     * @return objeto parroquia.
     */
    public Parroquia getParroquia() {
        return parroquia;
    }

    /**
     * Asigna el objeto parroquia.
     *
     * @param parroquia Objeto a ser asignado.
     */
    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<Canton> getCantonesB() {
        return cantonesB;
    }

    public List<Provincia> getProvinciasB() {
        return provinciasB;
    }
}
