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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La Clase EntregableDocumento representa a la tabla gpk_entregable_documento.
 * Relación entre los diferentes documentos que conforman un entregable
 *
 * @author JPA Generator
 * @version 1.0
 */
@Entity
@Table(name = "gpk_entregable_documento", catalog = "kaymantaGP", schema = "")
public class EntregableDocumento implements Serializable {

    /**
     * Constante autogenerada serialVersionUID.
     */
    private static final long serialVersionUID = 1854187131953829061L;
    /**
     * Propiedad que representa a la clave primaria de la entidad
     * EntregableDocumento.
     */
    @EmbeddedId
    private EntregableDocumentoPK pk = new EntregableDocumentoPK();
    /**
     * Fecha en la cual se definen que documentos corresponde a cada
     * entregable.. Representa a la columna FECHA.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;
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
     * Propiedad documento representa una relaci�n de muchos a uno con la
     * Entidad Documento.
     */
    @JoinColumn(name = "DOCUMENTO", referencedColumnName = "COD_DOCUMENTO", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Documento documento;
    /**
     * Propiedad actividadEntregable representa una relaci�n de muchos a uno con
     * la Entidad ActividadEntregable.
     */
    @JoinColumns({
        @JoinColumn(name = "ENTREGABLE", referencedColumnName = "COD_ACTIVIDAD_ENTREGABLE", insertable = false, updatable = false),
        @JoinColumn(name = "ACTIVIDAD", referencedColumnName = "ACTIVIDAD", insertable = false, updatable = false)
    })
    @ManyToOne(fetch = FetchType.EAGER)
    private ActividadEntregable actividadEntregable;

    /**
     * Crea una nueva instancia de la entidad EntregableDocumento.
     */
    public EntregableDocumento() {
    }

    /**
     * Crea una nueva instancia de la entidad EntregableDocumento de acuerdo a
     * su clave primaria.
     *
     * @param pk valor para la clave primaria de la entidad EntregableDocumento.
     */
    public EntregableDocumento(EntregableDocumentoPK pk) {
        this.pk = pk;
    }

    /**
     * Obtiene la clave primaria de la entidad EntregableDocumento.
     *
     * @return la clave primaria de la entidad EntregableDocumento.
     */
    public EntregableDocumentoPK getPk() {
        return pk;
    }

    /**
     * Asigna la clave primaria de la entidad EntregableDocumento.
     *
     * @param pk el valor a ser asignado a la clave primaria de la entidad
     * EntregableDocumento
     */
    public void setPk(EntregableDocumentoPK pk) {
        this.pk = pk;
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
     * Obtiene el valor de la propiedad documento relacionado con la entidad
     * Documento.
     *
     * @return el valor asignado a la propiedad documento
     */
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Asigna un valor a la propiedad documento, relacionado con la entidad
     * Documento.
     *
     * @param documento el valor a ser asignado a la propiedad documento
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    /**
     * Obtiene el valor de la propiedad actividadEntregable relacionado con la
     * entidad ActividadEntregable.
     *
     * @return el valor asignado a la propiedad actividadEntregable
     */
    public ActividadEntregable getActividadEntregable() {
        return actividadEntregable;
    }

    /**
     * Asigna un valor a la propiedad actividadEntregable, relacionado con la
     * entidad ActividadEntregable.
     *
     * @param actividadEntregable el valor a ser asignado a la propiedad
     * actividadEntregable
     */
    public void setActividadEntregable(ActividadEntregable actividadEntregable) {
        this.actividadEntregable = actividadEntregable;
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
        return "EntregableDocumento[pk=" + pk + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object) {
        boolean igual = true;
        if (!(object instanceof EntregableDocumento)) {
            igual = false;
        }
        EntregableDocumento other = (EntregableDocumento) object;
        if ((this.pk == null && other.pk != null)
                || (this.pk != null && !this.pk
                .equals(other.pk))) {
            igual = false;
        }
        return igual;
    }
}
