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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase Actividad representa a la tabla gpk_actividad. Actividad o
 * subactividad de un proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "GPK_ACTIVIDAD", catalog = "kaymantaGP", schema = "")
public class Actividad implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 460026836818407607L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Actividad, se
     * relaciona con la columna COD_ACTIVIDAD.
     */
    @Id
    @Column(name = "COD_ACTIVIDAD", nullable = false, length = 10)
    private Long codigo;
    /**
     * Llave foránea que referencia a un proyecto.. Representa a la columna
     * PROYECTO.
     */
    @Column(name = "PROYECTO", nullable = false)
    private Long codProyecto;
    /**
     * Llave foránea que referencia a una actividad padre.. Representa a la
     * columna SUBACTIVIDAD.
     */
    @Column(name = "SUBACTIVIDAD", nullable = false)
    private Long subactividad;
    /**
     * Nombre de la actividad o subactividad.. Representa a la columna
     * NOMBRE_ACTIVIDAD.
     */
    @Column(name = "NOMBRE_ACTIVIDAD", nullable = false, length = 100)
    private String nombreActividad;
    /**
     * Avance porcentual del avance de la actividad o subactividad.. Representa
     * a la columna AVANCE.
     */
    @Column(name = "AVANCE", nullable = false)
    private BigDecimal avance;
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
     * Propiedad proyecto representa una relación de muchos a uno con la Entidad
     * Proyecto.
     */
    @JoinColumn(name = "PROYECTO", referencedColumnName = "COD_PROYECTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto proyecto;
    /**
     * Propiedad actividad representa una relación de muchos a uno con la
     * Entidad Actividad.
     */
    @JoinColumn(name = "SUBACTIVIDAD", referencedColumnName = "COD_ACTIVIDAD", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad actividad;

    /**
     * Crea una nueva instancia de la entidad Actividad.
     */
    public Actividad() {
    }

    /**
     * Crea una nueva instancia de la entidad Actividad de acuerdo a su clave
     * primaria.
     *
     * @param codigo valor para la clave primaria de la entidad Actividad.
     */
    public Actividad(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el código de la entidad Actividad, se relaciona con la columna
     * COD_ACTIVIDAD.
     *
     * @return el código de la entidad Actividad
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Asigna el código de la entidad Actividad, se relaciona con la columna
     * COD_ACTIVIDAD.
     *
     * @param codigo el valor a ser asignado al código de la entidad Actividad
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el valor de la propiedad proyecto relacionado con la columna
     * PROYECTO.
     *
     * @return el valor asignado a la propiedad proyecto
     */
    public Long getCodProyecto() {
        return codProyecto;
    }

    /**
     * Asigna un valor a la propiedad proyecto, relacionado con la columna
     * PROYECTO.
     *
     * @param proyecto el valor a ser asignado a la propiedad proyecto
     */
    public void setCodProyecto(Long codProyecto) {
        this.codProyecto = codProyecto;
    }

    /**
     * Obtiene el valor de la propiedad subactividad relacionado con la columna
     * SUBACTIVIDAD.
     *
     * @return el valor asignado a la propiedad subactividad
     */
    public Long getSubactividad() {
        return subactividad;
    }

    /**
     * Asigna un valor a la propiedad subactividad, relacionado con la columna
     * SUBACTIVIDAD.
     *
     * @param subactividad el valor a ser asignado a la propiedad subactividad
     */
    public void setSubactividad(Long subactividad) {
        this.subactividad = subactividad;
    }

    /**
     * Obtiene el valor de la propiedad nombreActividad relacionado con la
     * columna NOMBRE_ACTIVIDAD.
     *
     * @return el valor asignado a la propiedad nombreActividad
     */
    public String getNombreActividad() {
        return nombreActividad;
    }

    /**
     * Asigna un valor a la propiedad nombreActividad, relacionado con la
     * columna NOMBRE_ACTIVIDAD.
     *
     * @param nombreActividad el valor a ser asignado a la propiedad
     * nombreActividad
     */
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
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
     * Obtiene el valor de la propiedad proyecto relacionado con la entidad
     * Proyecto.
     *
     * @return el valor asignado a la propiedad proyecto
     */
    public Proyecto getProyecto() {
        return proyecto;
    }

    /**
     * Asigna un valor a la propiedad proyecto, relacionado con la entidad
     * Proyecto.
     *
     * @param proyecto el valor a ser asignado a la propiedad proyecto
     */
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        hash += (codigo != null) ? codigo.hashCode() : 0;
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Actividad[codigo=" + codigo + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Actividad)) {
            igual = false;
        }
        Actividad other = (Actividad) object;
        if ((this.codigo == null && other.codigo != null)
                || (this.codigo != null && !this.codigo
                .equals(other.codigo))) {
            igual = false;
        }
        return igual;
    }
}
