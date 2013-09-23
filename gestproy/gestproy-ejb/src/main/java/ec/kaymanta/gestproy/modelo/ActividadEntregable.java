/*
 * KAYMANTA CIA. LTDA.
 * Sistema: GESTPROY 1.0 - KAYMANTA
 * Creado: 15-sep-2013 - 15:12:12
 * 
 * Los contenidos de este archivo son propiedad intelectual de KAYMANTA CIA. LTDA.
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de KAYMANTA CIA. LTDA.
 * 
 * Copyright 2013-2014 Kaymanta Todos los derechos reservados.
 */
package ec.kaymanta.gestproy.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase ActividadEntregable representa a la tabla gpk_actividad_entregable.
 * Entregable correspondiente a una actividad o subactividad.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
//@Table(name = "gpk_actividad_entregable", catalog = "swp_roche", schema = "dbo")
@Table(name = "GPK_ACTIVIDAD_ENTREGABLE", catalog = "kaymantaGP", schema = "")
public class ActividadEntregable implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = -7426285449231044635L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * ActividadEntregable.
     */
    @EmbeddedId
    private ActividadEntregablePK pk = new ActividadEntregablePK();
    /**
     * Llave foránea que referencia a la temática de un entregable.. Representa
     * a la columna TIPO_ENTREGABLE.
     */
    @Column(name = "TIPO_ENTREGABLE", nullable = false)
    private Long codTipoEntregable;
    /**
     * Llave foránea que referencia a la institución de control del entregable..
     * Representa a la columna INSTITUCION_CONTROL.
     */
    @Column(name = "INSTITUCION_CONTROL", nullable = false)
    private Long codInstitucionControl;
    /**
     * Nombre del entregable.. Representa a la columna NOMBRE_ENTREGABLE.
     */
    @Column(name = "NOMBRE_ENTREGABLE", nullable = false, length = 100)
    private String nombreEntregable;
    /**
     * Estado del entregable.. Representa a la columna ESTADO.
     */
    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado;
    /**
     * Usuario que crea el registro.. Representa a la columna USR_CREACION.
     */
    @Column(name = "USR_CREACION", nullable = false, length = 20)
    private String usrCreacion;
    /**
     * Fecha en la que el registro es creado.. Representa a la columna
     * FCREACION.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FCREACION", nullable = false)
    private Date fcreacion;
    /**
     * Último usuario que modifica el registro.. Representa a la columna
     * USR_MODIFICACION.
     */
    @Column(name = "USR_MODIFICACION", nullable = false, length = 20)
    private String usrModificacion;
    /**
     * Fecha en la que se modifica el registro.. Representa a la columna
     * FMODIFICACION.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FMODIFICACION", nullable = false)
    private Date fmodificacion;
    /**
     * Propiedad tipoEntregable representa una relación de muchos a uno con la
     * Entidad TipoEntregable.
     */
    @JoinColumn(name = "TIPO_ENTREGABLE", referencedColumnName = "COD_TIPO_DOCUMENTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoEntregable tipoEntregable;
    /**
     * Propiedad actividad representa una relación de muchos a uno con la
     * Entidad Actividad.
     */
    @JoinColumn(name = "ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad actividad;
    /**
     * Propiedad institucionControl representa una relación de muchos a uno con
     * la Entidad InstitucionControl.
     */
    @JoinColumn(name = "INSTITUCION_CONTROL", referencedColumnName = "COD_INSTITUCION_CONTROL", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private InstitucionControl institucionControl;

    /**
     * Crea una nueva instancia de la entidad ActividadEntregable.
     */
    public ActividadEntregable() {
    }

    /**
     * Crea una nueva instancia de la entidad ActividadEntregable de acuerdo a
     * su clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad ActividadEntregable.
     */
    public ActividadEntregable(ActividadEntregablePK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad ActividadEntregable.
     *
     * @return la clave primaria de la entidad ActividadEntregable.
     */
    public ActividadEntregablePK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad ActividadEntregable.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * ActividadEntregable
     */
    public void setPk(ActividadEntregablePK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad tipoEntregable relacionado con la
     * columna TIPO_ENTREGABLE.
     *
     * @return el valor asignado a la propiedad tipoEntregable
     */
    public Long getCodTipoEntregable() {
        return codTipoEntregable;
    }

    /**
     * Asigna un valor a la propiedad tipoEntregable, relacionado con la columna
     * TIPO_ENTREGABLE.
     *
     * @param tipoEntregable el valor a ser asignado a la propiedad
     * tipoEntregable
     */
    public void setCodTipoEntregable(Long codTipoEntregable) {
        this.codTipoEntregable = codTipoEntregable;
    }

    /**
     * Obtiene el valor de la propiedad institucionControl relacionado con la
     * columna INSTITUCION_CONTROL.
     *
     * @return el valor asignado a la propiedad institucionControl
     */
    public Long getCodInstitucionControl() {
        return codInstitucionControl;
    }

    /**
     * Asigna un valor a la propiedad institucionControl, relacionado con la
     * columna INSTITUCION_CONTROL.
     *
     * @param institucionControl el valor a ser asignado a la propiedad
     * institucionControl
     */
    public void setCodInstitucionControl(Long codInstitucionControl) {
        this.codInstitucionControl = codInstitucionControl;
    }

    /**
     * Obtiene el valor de la propiedad nombreEntregable relacionado con la
     * columna NOMBRE_ENTREGABLE.
     *
     * @return el valor asignado a la propiedad nombreEntregable
     */
    public String getNombreEntregable() {
        return nombreEntregable;
    }

    /**
     * Asigna un valor a la propiedad nombreEntregable, relacionado con la
     * columna NOMBRE_ENTREGABLE.
     *
     * @param nombreEntregable el valor a ser asignado a la propiedad
     * nombreEntregable
     */
    public void setNombreEntregable(String nombreEntregable) {
        this.nombreEntregable = nombreEntregable;
    }

    /**
     * Obtiene el valor de la propiedad estado relacionado con la columna
     * ESTADO.
     *
     * @return el valor asignado a la propiedad estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Asigna un valor a la propiedad estado, relacionado con la columna ESTADO.
     *
     * @param estado el valor a ser asignado a la propiedad estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el valor de la propiedad usrCreacion relacionado con la columna
     * USR_CREACION.
     *
     * @return el valor asignado a la propiedad usrCreacion
     */
    public String getUsrCreacion() {
        return usrCreacion;
    }

    /**
     * Asigna un valor a la propiedad usrCreacion, relacionado con la columna
     * USR_CREACION.
     *
     * @param usrCreacion el valor a ser asignado a la propiedad usrCreacion
     */
    public void setUsrCreacion(String usrCreacion) {
        this.usrCreacion = usrCreacion;
    }

    /**
     * Obtiene el valor de la propiedad fcreacion relacionado con la columna
     * FCREACION.
     *
     * @return el valor asignado a la propiedad fcreacion
     */
    public Date getFcreacion() {
        return fcreacion;
    }

    /**
     * Asigna un valor a la propiedad fcreacion, relacionado con la columna
     * FCREACION.
     *
     * @param fcreacion el valor a ser asignado a la propiedad fcreacion
     */
    public void setFcreacion(Date fcreacion) {
        this.fcreacion = fcreacion;
    }

    /**
     * Obtiene el valor de la propiedad usrModificacion relacionado con la
     * columna USR_MODIFICACION.
     *
     * @return el valor asignado a la propiedad usrModificacion
     */
    public String getUsrModificacion() {
        return usrModificacion;
    }

    /**
     * Asigna un valor a la propiedad usrModificacion, relacionado con la
     * columna USR_MODIFICACION.
     *
     * @param usrModificacion el valor a ser asignado a la propiedad
     * usrModificacion
     */
    public void setUsrModificacion(String usrModificacion) {
        this.usrModificacion = usrModificacion;
    }

    /**
     * Obtiene el valor de la propiedad fmodificacion relacionado con la columna
     * FMODIFICACION.
     *
     * @return el valor asignado a la propiedad fmodificacion
     */
    public Date getFmodificacion() {
        return fmodificacion;
    }

    /**
     * Asigna un valor a la propiedad fmodificacion, relacionado con la columna
     * FMODIFICACION.
     *
     * @param fmodificacion el valor a ser asignado a la propiedad fmodificacion
     */
    public void setFmodificacion(Date fmodificacion) {
        this.fmodificacion = fmodificacion;
    }

    /**
     * Obtiene el valor de la propiedad tipoEntregable relacionado con la
     * entidad TipoEntregable.
     *
     * @return el valor asignado a la propiedad tipoEntregable
     */
    public TipoEntregable getTipoEntregable() {
        return tipoEntregable;
    }

    /**
     * Asigna un valor a la propiedad tipoEntregable, relacionado con la entidad
     * TipoEntregable.
     *
     * @param tipoEntregable el valor a ser asignado a la propiedad
     * tipoEntregable
     */
    public void setTipoEntregable(TipoEntregable tipoEntregable) {
        this.tipoEntregable = tipoEntregable;
    }

    /**
     * Obtiene el valor de la propiedad actividad relacionado con la entidad
     * Actividad.
     *
     * @return el valor asignado a la propiedad actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Asigna un valor a la propiedad actividad, relacionado con la entidad
     * Actividad.
     *
     * @param actividad el valor a ser asignado a la propiedad actividad
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    /**
     * Obtiene el valor de la propiedad institucionControl relacionado con la
     * entidad InstitucionControl.
     *
     * @return el valor asignado a la propiedad institucionControl
     */
    public InstitucionControl getInstitucionControl() {
        return institucionControl;
    }

    /**
     * Asigna un valor a la propiedad institucionControl, relacionado con la
     * entidad InstitucionControl.
     *
     * @param institucionControl el valor a ser asignado a la propiedad
     * institucionControl
     */
    public void setInstitucionControl(InstitucionControl institucionControl) {
        this.institucionControl = institucionControl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null) ? pk.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ActividadEntregable[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ActividadEntregable)) {
            igual = false;
        }
        ActividadEntregable other = (ActividadEntregable) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
