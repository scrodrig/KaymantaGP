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
 * La Clase FechasActividad representa a la tabla gpk_fechas_actividad. Espacios
 * de tiempo que van variando de la planificación y desarrollo del proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_FECHAS_ACTIVIDAD", catalog = "kaymantaGP", schema = "")
public class FechasActividad implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 2846534533167070165L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * FechasActividad.
     */
    @EmbeddedId
    private FechasActividadPK pk = new FechasActividadPK();
    /**
     * Fecha de incio de la actividad.. Representa a la columna FINICIO.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FINICIO", nullable = false)
    private Date finicio;
    /**
     * Fecha de finalización de la actividad.. Representa a la columna FFIN.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FFIN")
    private Date ffin;
    /**
     * Fecha estimada para completar la actividad.. Representa a la columna
     * FESTIMADA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FESTIMADA")
    private Date festimada;
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
     * Propiedad actividad representa una relación de muchos a uno con la
     * Entidad Actividad.
     */
    @JoinColumn(name = "ACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad actividad;

    /**
     * Crea una nueva instancia de la entidad FechasActividad.
     */
    public FechasActividad() {
    }

    /**
     * Crea una nueva instancia de la entidad FechasActividad de acuerdo a su
     * clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad FechasActividad.
     */
    public FechasActividad(FechasActividadPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad FechasActividad.
     *
     * @return la clave primaria de la entidad FechasActividad.
     */
    public FechasActividadPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad FechasActividad.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * FechasActividad
     */
    public void setPk(FechasActividadPK pk) {
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
     * Obtiene el valor de la propiedad festimada relacionado con la columna
     * FESTIMADA.
     *
     * @return el valor asignado a la propiedad festimada
     */
    public Date getFestimada() {
        return festimada;
    }

    /**
     * Asigna un valor a la propiedad festimada, relacionado con la columna
     * FESTIMADA.
     *
     * @param festimada el valor a ser asignado a la propiedad festimada
     */
    public void setFestimada(Date festimada) {
        this.festimada = festimada;
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
        return "FechasActividad[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof FechasActividad)) {
            igual = false;
        }
        FechasActividad other = (FechasActividad) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
