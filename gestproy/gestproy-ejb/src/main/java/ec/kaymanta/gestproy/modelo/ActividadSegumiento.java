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
import java.math.BigDecimal;
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
 * La Clase ActividadSegumiento representa a la tabla gpk_actividad_segumiento.
 * Seguimiento de una activdad o subactividad particular de un proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_ACTIVIDAD_SEGUMIENTO", catalog = "kaymantaGP", schema = "")
public class ActividadSegumiento implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 5190697199146962453L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * ActividadSegumiento.
     */
    @EmbeddedId
    private ActividadSegumientoPK pk = new ActividadSegumientoPK();
    /**
     * Llave que referencia al supervisor o certificador del avance de una
     * actividad o subactividad.. Representa a la columna SUPERVISOR.
     */
    @Column(name = "SUPERVISOR", nullable = false, length = 10)
    private String supervisor;
    /**
     * Estado de la actividad.. Representa a la columna ESTADO.
     */
    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado;    
    /**
     * Avance porcentual de una actividad.. Representa a la columna AVANCE.
     */
    @Column(name = "RESPONSABLE", nullable = false, length = 10)
    private String responsable;
    
    /**
     * Avance porcentual de una actividad.. Representa a la columna AVANCE.
     */
    @Column(name = "AVANCE", nullable = false)
    private BigDecimal avance;
    
    /**
     * Fecha en la que el registro es creado.. Representa a la columna
     * FCREACION.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FTRABAJO")
    private Date ftrabajo;
    
    /**
     * Campo para describir lo que se hizo en el avance.. Representa a la columna AVANCE.
     */
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    
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
     * Propiedad empleado representa una relación de muchos a uno con la Entidad Empleado.
     */
    @JoinColumn(name = "SUPERVISOR", referencedColumnName = "CEDULA", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Empleado empleado;
    /**
     * Propiedad actividad representa una relación de muchos a uno con la
     * Entidad Actividad.
     */
    @JoinColumn(name = "ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad actividad;

    /**
     * Crea una nueva instancia de la entidad ActividadSegumiento.
     */
    public ActividadSegumiento() {
    }

    /**
     * Crea una nueva instancia de la entidad ActividadSegumiento de acuerdo a
     * su clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad ActividadSegumiento.
     */
    public ActividadSegumiento(ActividadSegumientoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad ActividadSegumiento.
     *
     * @return la clave primaria de la entidad ActividadSegumiento.
     */
    public ActividadSegumientoPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad ActividadSegumiento.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * ActividadSegumiento
     */
    public void setPk(ActividadSegumientoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad supervisor relacionado con la columna
     * SUPERVISOR.
     *
     * @return el valor asignado a la propiedad supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * Asigna un valor a la propiedad supervisor, relacionado con la columna
     * SUPERVISOR.
     *
     * @param supervisor el valor a ser asignado a la propiedad supervisor
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
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
     * Obtiene el valor de la propiedad avance relacionado con la columna
     * AVANCE.
     *
     * @return el valor asignado a la propiedad avance
     */
    public BigDecimal getAvance() {
        return avance;
    }

    /**
     * Asigna un valor a la propiedad avance, relacionado con la columna AVANCE.
     *
     * @param avance el valor a ser asignado a la propiedad avance
     */
    public void setAvance(BigDecimal avance) {
        this.avance = avance;
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
     * Obtiene el valor de la propiedad empleado relacionado con la entidad
     * Empleado.
     *
     * @return el valor asignado a la propiedad empleado
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Asigna un valor a la propiedad empleado, relacionado con la entidad
     * Empleado.
     *
     * @param empleado el valor a ser asignado a la propiedad empleado
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFtrabajo() {
        return ftrabajo;
    }

    public void setFtrabajo(Date ftrabajo) {
        this.ftrabajo = ftrabajo;
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
        return "ActividadSegumiento[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ActividadSegumiento)) {
            igual = false;
        }
        ActividadSegumiento other = (ActividadSegumiento) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
