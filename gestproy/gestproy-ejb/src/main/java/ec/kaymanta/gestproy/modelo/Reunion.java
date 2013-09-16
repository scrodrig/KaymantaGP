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
 * La Clase Reunion representa a la tabla gpk_reunion. Reuniones que se ejecutan
 * a fines de coordinar las acciones de un proyecto.
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_reunion", catalog = "kaymantaGP", schema = "")
public class Reunion implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 1739823769684113574L;
    /**
     * Propiedad que representa a la clave primaria de la entidad Reunion.
     */
    @EmbeddedId
    private ReunionPK pk = new ReunionPK();
    /**
     * Locación en la que tienen sitio la reunión.. Representa a la columna
     * LUGAR.
     */
    @Column(name = "LUGAR", nullable = false, length = 50)
    private String lugar;
    /**
     * Fecha en la que se lleva a cabo la reunión.. Representa a la columna
     * FECHA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
    /**
     * Personas asistentes a la reunión.. Representa a la columna ASISTENTES.
     */
    @Column(name = "ASISTENTES", nullable = false, length = 65535)
    private String asistentes;
    /**
     * Novedades que se registran en la reunión.. Representa a la columna
     * NOVEDADES.
     */
    @Column(name = "NOVEDADES", nullable = false, length = 65535)
    private String novedades;
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
     * Último usuario que modifica el registro. Representa a la columna
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
     * Crea una nueva instancia de la entidad Reunion.
     */
    public Reunion() {
    }

    /**
     * Crea una nueva instancia de la entidad Reunion de acuerdo a su clave
     * primaria.
     *
     * @param pk valor para la clave primaria de la entidad Reunion.
     */
    public Reunion(ReunionPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad Reunion.
     *
     * @return la clave primaria de la entidad Reunion.
     */
    public ReunionPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad Reunion.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * Reunion
     */
    public void setPk(ReunionPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene el valor de la propiedad lugar relacionado con la columna LUGAR.
     *
     * @return el valor asignado a la propiedad lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Asigna un valor a la propiedad lugar, relacionado con la columna LUGAR.
     *
     * @param lugar el valor a ser asignado a la propiedad lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Obtiene el valor de la propiedad fecha relacionado con la columna FECHA.
     *
     * @return el valor asignado a la propiedad fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Asigna un valor a la propiedad fecha, relacionado con la columna FECHA.
     *
     * @param fecha el valor a ser asignado a la propiedad fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el valor de la propiedad asistentes relacionado con la columna
     * ASISTENTES.
     *
     * @return el valor asignado a la propiedad asistentes
     */
    public String getAsistentes() {
        return asistentes;
    }

    /**
     * Asigna un valor a la propiedad asistentes, relacionado con la columna
     * ASISTENTES.
     *
     * @param asistentes el valor a ser asignado a la propiedad asistentes
     */
    public void setAsistentes(String asistentes) {
        this.asistentes = asistentes;
    }

    /**
     * Obtiene el valor de la propiedad novedades relacionado con la columna
     * NOVEDADES.
     *
     * @return el valor asignado a la propiedad novedades
     */
    public String getNovedades() {
        return novedades;
    }

    /**
     * Asigna un valor a la propiedad novedades, relacionado con la columna
     * NOVEDADES.
     *
     * @param novedades el valor a ser asignado a la propiedad novedades
     */
    public void setNovedades(String novedades) {
        this.novedades = novedades;
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
        return "Reunion[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof Reunion)) {
            igual = false;
        }
        Reunion other = (Reunion) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
