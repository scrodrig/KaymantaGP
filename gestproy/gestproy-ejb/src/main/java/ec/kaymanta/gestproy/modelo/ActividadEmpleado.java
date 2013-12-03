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
 * La Clase ActividadEmpleado representa a la tabla gpk_actividad_empleado.
 * Asignación de los responsables de una actividad o subactividad.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_ACTIVIDAD_EMPLEADO", catalog = "kaymantaGP", schema = "")
public class ActividadEmpleado implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 2191133311771871163L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * ActividadEmpleado.
     */
    @EmbeddedId
    private ActividadEmpleadoPK pk = new ActividadEmpleadoPK();
    /**
     * Fecha inicial en la que se responsabiliza a un responsable.. Representa a
     * la columna FINICIO.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FINICIO", nullable = false)
    private Date finicio;
    /**
     * Fecha en la que el empleado deja de ser responsable de la actividad..
     * Representa a la columna FFIN.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FFIN", nullable = false)
    private Date ffin;
     /**
     * Avance porcentual del avance del proyecto.. Representa a la columna
     * AVANCE.
     */
    @Column(name = "AVANCE", nullable = false)
    private BigDecimal avance;
    /**
     * Horas Diarias Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_DIA_EST", nullable = false)
    private Long hDiaEst;
    
    /**
     * Horas Diarias Reales.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_DIA_REAL", nullable = false)
    private Long hDiaReal;
    
    /**
     * Horas Trabajadas Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_TRAB_EST", nullable = false)
    private BigDecimal hTrabEst;
    
    /**
     * Horas Trabajadas Real.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "HORAS_TRAB_REAL", nullable = false)
    private BigDecimal hTrabReal;
    
    /**
     * Trabajo total Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "TRABAJO_TOTAL_EST", nullable = false)
    private Long tTotalEst;
    
    /**
     * Trabajo Total Estimadas.. Representa
     * a la columna HORAS_DIA_EST.
     */
    @Column(name = "TRABAJO_TOTAL_REAL", nullable = false)
    private Long tTotalReal;
    
    /**
     * Muestra si el empleado es o no responsable.. Representa a la columna
     * ES_RESPONSABLE.
     */
    
    
    
    
    @Column(name = "ES_RESPONSABLE", nullable = false, length = 1)
    private String esResponsable;
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
     * Propiedad empleado representa una relación de muchos a uno con la Entidad
     * Empleado.
     */
    @JoinColumn(name = "RESPONSABLE", referencedColumnName = "CEDULA", insertable = false, updatable = false)
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
     * Crea una nueva instancia de la entidad ActividadEmpleado.
     */
    public ActividadEmpleado() {
    }

    /**
     * Crea una nueva instancia de la entidad ActividadEmpleado de acuerdo a su
     * clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad ActividadEmpleado.
     */
    public ActividadEmpleado(ActividadEmpleadoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad ActividadEmpleado.
     *
     * @return la clave primaria de la entidad ActividadEmpleado.
     */
    public ActividadEmpleadoPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad ActividadEmpleado.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * ActividadEmpleado
     */
    public void setPk(ActividadEmpleadoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad finicio relacionado con la columna
     * FINICIO.
     *
     * @return el valor asignado a la propiedad finicio
     */
    public Date getFinicio() {
        return finicio;
    }

    /**
     * Asigna un valor a la propiedad finicio, relacionado con la columna
     * FINICIO.
     *
     * @param finicio el valor a ser asignado a la propiedad finicio
     */
    public void setFinicio(Date finicio) {
        this.finicio = finicio;
    }

    /**
     * Obtiene el valor de la propiedad ffin relacionado con la columna FFIN.
     *
     * @return el valor asignado a la propiedad ffin
     */
    public Date getFfin() {
        return ffin;
    }

    /**
     * Asigna un valor a la propiedad ffin, relacionado con la columna FFIN.
     *
     * @param ffin el valor a ser asignado a la propiedad ffin
     */
    public void setFfin(Date ffin) {
        this.ffin = ffin;
    }

    /**
     * Obtiene el valor de la propiedad tiempoEmpleado relacionado con la
     * columna TIEMPO_EMPLEADO.
     *
     * @return el valor asignado a la propiedad tiempoEmpleado
     */
    /**
     * Obtiene el valor de la propiedad esResponsable relacionado con la columna
     * ES_RESPONSABLE.
     *
     * @return el valor asignado a la propiedad esResponsable
     */
    public String getEsResponsable() {
        return esResponsable;
    }

    /**
     * Asigna un valor a la propiedad esResponsable, relacionado con la columna
     * ES_RESPONSABLE.
     *
     * @param esResponsable el valor a ser asignado a la propiedad esResponsable
     */
    public void setEsResponsable(String esResponsable) {
        this.esResponsable = esResponsable;
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

    public BigDecimal getAvance() {
        return avance;
    }

    public void setAvance(BigDecimal avance) {
        this.avance = avance;
    }

    public Long gethDiaEst() {
        return hDiaEst;
    }

    public void sethDiaEst(Long hDiaEst) {
        this.hDiaEst = hDiaEst;
    }

    public Long gethDiaReal() {
        return hDiaReal;
    }

    public void sethDiaReal(Long hDiaReal) {
        this.hDiaReal = hDiaReal;
    }

    public BigDecimal gethTrabEst() {
        return hTrabEst;
    }

    public void sethTrabEst(BigDecimal hTrabEst) {
        this.hTrabEst = hTrabEst;
    }

    public BigDecimal gethTrabReal() {
        return hTrabReal;
    }

    public void sethTrabReal(BigDecimal hTrabReal) {
        this.hTrabReal = hTrabReal;
    }

    public Long gettTotalEst() {
        return tTotalEst;
    }

    public void settTotalEst(Long tTotalEst) {
        this.tTotalEst = tTotalEst;
    }

    public Long gettTotalReal() {
        return tTotalReal;
    }

    public void settTotalReal(Long tTotalReal) {
        this.tTotalReal = tTotalReal;
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
        return "ActividadEmpleado[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof ActividadEmpleado)) {
            igual = false;
        }
        ActividadEmpleado other = (ActividadEmpleado) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
